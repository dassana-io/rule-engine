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

json_path_condition: json_path generic_operators         #  GenericJsonPathCondition
                   | json_path operator comparison_value #  JsonPathCondition
;

operator:
        | string_operators
        | number_operators
;

generic_operators: 'exists' #ExistsOperator
                  | 'does not exist' #DoesNotExistOperator
                  | 'is empty' #EmptyOperator
                  ;

number_operators: 'greater than' #NumberGreaterThanOperator
;

string_operators: 'contains' #StringContainsExpression
                | 'is'       #StringEqualsOperator
                ;

json_path :  IDENTIFIER|'$.'IDENTIFIER| STRING ;

comparison_value  : INT | NUMERIC_VALUE | IDENTIFIER | STRING ;

STRING : '"' ( '\\"' | . )*? '"'
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