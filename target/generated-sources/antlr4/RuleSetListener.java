// Generated from RuleSet.g4 by ANTLR 4.9.1
 //parser
    package app.dassana.rules;
    import app.dassana.rules.RuleSetListener;
    import app.dassana.rules.RuleSetParser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RuleSetParser}.
 */
public interface RuleSetListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RuleSetParser#rule_set}.
	 * @param ctx the parse tree
	 */
	void enterRule_set(RuleSetParser.Rule_setContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleSetParser#rule_set}.
	 * @param ctx the parse tree
	 */
	void exitRule_set(RuleSetParser.Rule_setContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleSetParser#single_rule}.
	 * @param ctx the parse tree
	 */
	void enterSingle_rule(RuleSetParser.Single_ruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleSetParser#single_rule}.
	 * @param ctx the parse tree
	 */
	void exitSingle_rule(RuleSetParser.Single_ruleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LogicalExpressionAnd}
	 * labeled alternative in {@link RuleSetParser#logical_expr}.
	 * @param ctx the parse tree
	 */
	void enterLogicalExpressionAnd(RuleSetParser.LogicalExpressionAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LogicalExpressionAnd}
	 * labeled alternative in {@link RuleSetParser#logical_expr}.
	 * @param ctx the parse tree
	 */
	void exitLogicalExpressionAnd(RuleSetParser.LogicalExpressionAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LogicalExpressionNot}
	 * labeled alternative in {@link RuleSetParser#logical_expr}.
	 * @param ctx the parse tree
	 */
	void enterLogicalExpressionNot(RuleSetParser.LogicalExpressionNotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LogicalExpressionNot}
	 * labeled alternative in {@link RuleSetParser#logical_expr}.
	 * @param ctx the parse tree
	 */
	void exitLogicalExpressionNot(RuleSetParser.LogicalExpressionNotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LogicalExpressionOr}
	 * labeled alternative in {@link RuleSetParser#logical_expr}.
	 * @param ctx the parse tree
	 */
	void enterLogicalExpressionOr(RuleSetParser.LogicalExpressionOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LogicalExpressionOr}
	 * labeled alternative in {@link RuleSetParser#logical_expr}.
	 * @param ctx the parse tree
	 */
	void exitLogicalExpressionOr(RuleSetParser.LogicalExpressionOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SpecificationExpression}
	 * labeled alternative in {@link RuleSetParser#logical_expr}.
	 * @param ctx the parse tree
	 */
	void enterSpecificationExpression(RuleSetParser.SpecificationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SpecificationExpression}
	 * labeled alternative in {@link RuleSetParser#logical_expr}.
	 * @param ctx the parse tree
	 */
	void exitSpecificationExpression(RuleSetParser.SpecificationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TotalledNumericGreaterThanComparisonSpecificationExpression}
	 * labeled alternative in {@link RuleSetParser#specification_expr}.
	 * @param ctx the parse tree
	 */
	void enterTotalledNumericGreaterThanComparisonSpecificationExpression(RuleSetParser.TotalledNumericGreaterThanComparisonSpecificationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TotalledNumericGreaterThanComparisonSpecificationExpression}
	 * labeled alternative in {@link RuleSetParser#specification_expr}.
	 * @param ctx the parse tree
	 */
	void exitTotalledNumericGreaterThanComparisonSpecificationExpression(RuleSetParser.TotalledNumericGreaterThanComparisonSpecificationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumericGreaterThanComparisonSpecificationExpression}
	 * labeled alternative in {@link RuleSetParser#specification_expr}.
	 * @param ctx the parse tree
	 */
	void enterNumericGreaterThanComparisonSpecificationExpression(RuleSetParser.NumericGreaterThanComparisonSpecificationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumericGreaterThanComparisonSpecificationExpression}
	 * labeled alternative in {@link RuleSetParser#specification_expr}.
	 * @param ctx the parse tree
	 */
	void exitNumericGreaterThanComparisonSpecificationExpression(RuleSetParser.NumericGreaterThanComparisonSpecificationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TotalledNumericLessThanComparisonSpecificationExpression}
	 * labeled alternative in {@link RuleSetParser#specification_expr}.
	 * @param ctx the parse tree
	 */
	void enterTotalledNumericLessThanComparisonSpecificationExpression(RuleSetParser.TotalledNumericLessThanComparisonSpecificationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TotalledNumericLessThanComparisonSpecificationExpression}
	 * labeled alternative in {@link RuleSetParser#specification_expr}.
	 * @param ctx the parse tree
	 */
	void exitTotalledNumericLessThanComparisonSpecificationExpression(RuleSetParser.TotalledNumericLessThanComparisonSpecificationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumericLessThanComparisonSpecificationExpression}
	 * labeled alternative in {@link RuleSetParser#specification_expr}.
	 * @param ctx the parse tree
	 */
	void enterNumericLessThanComparisonSpecificationExpression(RuleSetParser.NumericLessThanComparisonSpecificationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumericLessThanComparisonSpecificationExpression}
	 * labeled alternative in {@link RuleSetParser#specification_expr}.
	 * @param ctx the parse tree
	 */
	void exitNumericLessThanComparisonSpecificationExpression(RuleSetParser.NumericLessThanComparisonSpecificationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringEqualsComparisonSpecificationExpression}
	 * labeled alternative in {@link RuleSetParser#specification_expr}.
	 * @param ctx the parse tree
	 */
	void enterStringEqualsComparisonSpecificationExpression(RuleSetParser.StringEqualsComparisonSpecificationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringEqualsComparisonSpecificationExpression}
	 * labeled alternative in {@link RuleSetParser#specification_expr}.
	 * @param ctx the parse tree
	 */
	void exitStringEqualsComparisonSpecificationExpression(RuleSetParser.StringEqualsComparisonSpecificationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringContainsComparisonSpecificationExpression}
	 * labeled alternative in {@link RuleSetParser#specification_expr}.
	 * @param ctx the parse tree
	 */
	void enterStringContainsComparisonSpecificationExpression(RuleSetParser.StringContainsComparisonSpecificationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringContainsComparisonSpecificationExpression}
	 * labeled alternative in {@link RuleSetParser#specification_expr}.
	 * @param ctx the parse tree
	 */
	void exitStringContainsComparisonSpecificationExpression(RuleSetParser.StringContainsComparisonSpecificationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanIsTrueComparisonSpecificationExpression}
	 * labeled alternative in {@link RuleSetParser#specification_expr}.
	 * @param ctx the parse tree
	 */
	void enterBooleanIsTrueComparisonSpecificationExpression(RuleSetParser.BooleanIsTrueComparisonSpecificationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanIsTrueComparisonSpecificationExpression}
	 * labeled alternative in {@link RuleSetParser#specification_expr}.
	 * @param ctx the parse tree
	 */
	void exitBooleanIsTrueComparisonSpecificationExpression(RuleSetParser.BooleanIsTrueComparisonSpecificationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanIsFalseComparisonSpecificationExpression}
	 * labeled alternative in {@link RuleSetParser#specification_expr}.
	 * @param ctx the parse tree
	 */
	void enterBooleanIsFalseComparisonSpecificationExpression(RuleSetParser.BooleanIsFalseComparisonSpecificationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanIsFalseComparisonSpecificationExpression}
	 * labeled alternative in {@link RuleSetParser#specification_expr}.
	 * @param ctx the parse tree
	 */
	void exitBooleanIsFalseComparisonSpecificationExpression(RuleSetParser.BooleanIsFalseComparisonSpecificationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PathExistsExpression}
	 * labeled alternative in {@link RuleSetParser#specification_expr}.
	 * @param ctx the parse tree
	 */
	void enterPathExistsExpression(RuleSetParser.PathExistsExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PathExistsExpression}
	 * labeled alternative in {@link RuleSetParser#specification_expr}.
	 * @param ctx the parse tree
	 */
	void exitPathExistsExpression(RuleSetParser.PathExistsExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PathIsNotEmptyExpression}
	 * labeled alternative in {@link RuleSetParser#specification_expr}.
	 * @param ctx the parse tree
	 */
	void enterPathIsNotEmptyExpression(RuleSetParser.PathIsNotEmptyExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PathIsNotEmptyExpression}
	 * labeled alternative in {@link RuleSetParser#specification_expr}.
	 * @param ctx the parse tree
	 */
	void exitPathIsNotEmptyExpression(RuleSetParser.PathIsNotEmptyExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayIncludesOneComparisonSpecificationExpression}
	 * labeled alternative in {@link RuleSetParser#specification_expr}.
	 * @param ctx the parse tree
	 */
	void enterArrayIncludesOneComparisonSpecificationExpression(RuleSetParser.ArrayIncludesOneComparisonSpecificationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayIncludesOneComparisonSpecificationExpression}
	 * labeled alternative in {@link RuleSetParser#specification_expr}.
	 * @param ctx the parse tree
	 */
	void exitArrayIncludesOneComparisonSpecificationExpression(RuleSetParser.ArrayIncludesOneComparisonSpecificationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleSetParser#left_arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterLeft_arithmetic_expr(RuleSetParser.Left_arithmetic_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleSetParser#left_arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitLeft_arithmetic_expr(RuleSetParser.Left_arithmetic_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleSetParser#right_arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterRight_arithmetic_expr(RuleSetParser.Right_arithmetic_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleSetParser#right_arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitRight_arithmetic_expr(RuleSetParser.Right_arithmetic_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArithmeticExpressionMult}
	 * labeled alternative in {@link RuleSetParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticExpressionMult(RuleSetParser.ArithmeticExpressionMultContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArithmeticExpressionMult}
	 * labeled alternative in {@link RuleSetParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticExpressionMult(RuleSetParser.ArithmeticExpressionMultContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArithmeticExpressionMinus}
	 * labeled alternative in {@link RuleSetParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticExpressionMinus(RuleSetParser.ArithmeticExpressionMinusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArithmeticExpressionMinus}
	 * labeled alternative in {@link RuleSetParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticExpressionMinus(RuleSetParser.ArithmeticExpressionMinusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArithmeticExpressionNumericEntity}
	 * labeled alternative in {@link RuleSetParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticExpressionNumericEntity(RuleSetParser.ArithmeticExpressionNumericEntityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArithmeticExpressionNumericEntity}
	 * labeled alternative in {@link RuleSetParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticExpressionNumericEntity(RuleSetParser.ArithmeticExpressionNumericEntityContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArithmeticExpressionDiv}
	 * labeled alternative in {@link RuleSetParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticExpressionDiv(RuleSetParser.ArithmeticExpressionDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArithmeticExpressionDiv}
	 * labeled alternative in {@link RuleSetParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticExpressionDiv(RuleSetParser.ArithmeticExpressionDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArithmeticExpressionPlus}
	 * labeled alternative in {@link RuleSetParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticExpressionPlus(RuleSetParser.ArithmeticExpressionPlusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArithmeticExpressionPlus}
	 * labeled alternative in {@link RuleSetParser#arithmetic_expr}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticExpressionPlus(RuleSetParser.ArithmeticExpressionPlusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TotalledJsonPathExpression}
	 * labeled alternative in {@link RuleSetParser#numeric_expr}.
	 * @param ctx the parse tree
	 */
	void enterTotalledJsonPathExpression(RuleSetParser.TotalledJsonPathExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TotalledJsonPathExpression}
	 * labeled alternative in {@link RuleSetParser#numeric_expr}.
	 * @param ctx the parse tree
	 */
	void exitTotalledJsonPathExpression(RuleSetParser.TotalledJsonPathExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code JsonPathExpression}
	 * labeled alternative in {@link RuleSetParser#numeric_expr}.
	 * @param ctx the parse tree
	 */
	void enterJsonPathExpression(RuleSetParser.JsonPathExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code JsonPathExpression}
	 * labeled alternative in {@link RuleSetParser#numeric_expr}.
	 * @param ctx the parse tree
	 */
	void exitJsonPathExpression(RuleSetParser.JsonPathExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumericConstant}
	 * labeled alternative in {@link RuleSetParser#numeric_expr}.
	 * @param ctx the parse tree
	 */
	void enterNumericConstant(RuleSetParser.NumericConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumericConstant}
	 * labeled alternative in {@link RuleSetParser#numeric_expr}.
	 * @param ctx the parse tree
	 */
	void exitNumericConstant(RuleSetParser.NumericConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleSetParser#value_expr}.
	 * @param ctx the parse tree
	 */
	void enterValue_expr(RuleSetParser.Value_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleSetParser#value_expr}.
	 * @param ctx the parse tree
	 */
	void exitValue_expr(RuleSetParser.Value_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleSetParser#total_expr}.
	 * @param ctx the parse tree
	 */
	void enterTotal_expr(RuleSetParser.Total_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleSetParser#total_expr}.
	 * @param ctx the parse tree
	 */
	void exitTotal_expr(RuleSetParser.Total_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleSetParser#jsonpath_expr}.
	 * @param ctx the parse tree
	 */
	void enterJsonpath_expr(RuleSetParser.Jsonpath_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleSetParser#jsonpath_expr}.
	 * @param ctx the parse tree
	 */
	void exitJsonpath_expr(RuleSetParser.Jsonpath_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleSetParser#jsonpath_dotnotation_expr}.
	 * @param ctx the parse tree
	 */
	void enterJsonpath_dotnotation_expr(RuleSetParser.Jsonpath_dotnotation_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleSetParser#jsonpath_dotnotation_expr}.
	 * @param ctx the parse tree
	 */
	void exitJsonpath_dotnotation_expr(RuleSetParser.Jsonpath_dotnotation_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleSetParser#dotnotation_expr}.
	 * @param ctx the parse tree
	 */
	void enterDotnotation_expr(RuleSetParser.Dotnotation_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleSetParser#dotnotation_expr}.
	 * @param ctx the parse tree
	 */
	void exitDotnotation_expr(RuleSetParser.Dotnotation_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleSetParser#identifierWithQualifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierWithQualifier(RuleSetParser.IdentifierWithQualifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleSetParser#identifierWithQualifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierWithQualifier(RuleSetParser.IdentifierWithQualifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleSetParser#query_expr}.
	 * @param ctx the parse tree
	 */
	void enterQuery_expr(RuleSetParser.Query_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleSetParser#query_expr}.
	 * @param ctx the parse tree
	 */
	void exitQuery_expr(RuleSetParser.Query_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleSetParser#string_comparison_value}.
	 * @param ctx the parse tree
	 */
	void enterString_comparison_value(RuleSetParser.String_comparison_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleSetParser#string_comparison_value}.
	 * @param ctx the parse tree
	 */
	void exitString_comparison_value(RuleSetParser.String_comparison_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link RuleSetParser#string_array}.
	 * @param ctx the parse tree
	 */
	void enterString_array(RuleSetParser.String_arrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link RuleSetParser#string_array}.
	 * @param ctx the parse tree
	 */
	void exitString_array(RuleSetParser.String_arrayContext ctx);
}