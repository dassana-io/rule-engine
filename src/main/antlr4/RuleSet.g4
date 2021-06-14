grammar RuleSet;

options {
    language = Java;
}
@header { //parser
    package app.dassana.rules;
    import app.dassana.rules.RuleSetListener;
    import app.dassana.rules.RuleSetParser;
}

rule_set : single_rule (NEWLINE single_rule)* EOF ;

single_rule : logical_expr RULECOMMENT? ;

logical_expr : LPAREN logical_expr RPAREN      # ParenExpression
             | logical_expr 'and' logical_expr # LogicalExpressionAnd
             | logical_expr 'or' logical_expr  # LogicalExpressionOr
             | 'not' logical_expr              # LogicalExpressionNot
             | json_path_condition            # JsonPathConditionExpression
             ;

json_path_condition: json_path operator                         #  GenericJsonPathCondition
                   | json_path operator string_comparison_value #  JsonPathCondition
;

operator: generic_operators
        | string_operators
        | number_operators
;

generic_operators: 'exists' #ExistsOperator
                  | 'does not exist' #DoesNotExistOperator
                  ;

number_operators: 'greater than' #NumberGreaterThanOperator
;

string_operators: 'contains' #StringContainsExpression
                | 'is'       #StringEqualsOperator
                ;

json_path : .*? ;

/*the original code had json path parser, but I don't think we really need it
 it gets more and more difficult to maintain it so we are using .*? and let the jayway library take care of parsing etc for us
*/
jsonpath_expr : jsonpath_dotnotation_expr
              ;

// This is standard JsonPath using Dot notation
jsonpath_dotnotation_expr : '$.' dotnotation_expr ('.' dotnotation_expr)* ;

dotnotation_expr : identifierWithQualifier
                 | identifierWithQualifier.IDENTIFIER
                 | IDENTIFIER
                 ;

identifierWithQualifier : IDENTIFIER '[]'
                        | IDENTIFIER '[*]'
                        | IDENTIFIER '[' INT ']'
                        | IDENTIFIER '[?(' query_expr ')]'
                        ;

query_expr : query_expr ('&&' query_expr)+
           | query_expr ('||' query_expr)+
           | '@.' IDENTIFIER
           | '@.' IDENTIFIER '>' INT
           | '@.' IDENTIFIER '<' INT
           | '@.length-' INT
           | '@.' IDENTIFIER '==' INT
           | '@.' IDENTIFIER '==' '\'' IDENTIFIER '\''
           | '@.' IDENTIFIER 'contains'  STRING_LITERAL
           ;

string_comparison_value  : INT | NUMERIC_VALUE | IDENTIFIER | STRING_LITERAL ;

string_array : '(' IDENTIFIER (',' IDENTIFIER)* ')' ;

STRING_LITERAL :
                '"' (~('"' | '\\' | '\r' | '\n') | '\\' ('"' | '\\'))* '"'
                {setText(getText().substring(1, getText().length()-1));}
                ;
INT           : '0' | [1-9][0-9]* ;
NUMERIC_VALUE : '-'?[0-9]+('.'[0-9]+)? ;
IDENTIFIER    : [a-z.A-Z_-][a-z.A-Z_0-9-]* ;
RULECOMMENT   : '#' ~[\r\n]*;
NEWLINE       : '\r'? '\n';

LPAREN     : '(' ;
RPAREN     : ')' ;

WS : [ \r\t\u000C\n]+ -> skip ;