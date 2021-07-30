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
             | jq_path_condition            # JQPathConditionExpression
             ;

jq_path_condition: STRING                            # JqOperation
                   |jq_path generic_operators         #  GenericOperations
                   | jq_path string_operators string_comparison_value #  StringOperations
                   | jq_path number_operators number_comparision_value # NumberOperations
                   | jq_path boolean_operators #BoolOperations
;

number_comparision_value: INT|NUMERIC_VALUE;
string_comparison_value: STRING|IDENTIFIER;


boolean_operators: 'is true'# BoolIstrueOperator
                   |'is false' # BoolIsFaleOperator
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

jq_path :  IDENTIFIER| STRING ;

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