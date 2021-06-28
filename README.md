
### Introduction

This library implements _JSON Rule_ evaluation used internally by the Dassana engine. The grammar of a JSON rule can be found at `src/main/antlr4/RuleSet.g4`

The original code can be found [here](https://github.com/stevenalexander/rules-evaluator). The grammar has been significantly changed allowing for easier addition of rule operators.

#### JSON RULE

A JSON rule is simply a JSON path followed by operator and value:  
`<jsonpath> <operator> <value>`

The value can be optional in the case of generic operators.

We use the [jayway library](https://github.com/json-path/JsonPath) for JSON path implementation. For testing purpose, we highly recommend using https://jsonpath.herokuapp.com/ (make sure `Jayway` is selected in the right-side panel)

Following operators are available-

1. Generic operators :  
   `exists`  
   `does not exist`

2. Data type based operatos:
    1. String operators:  
       `contains`  
       `is`
    3. Numeric operators :  
       `greater than`

A lot many other operators will be made available in future releases.

You can read the ANTLR grammar located at `src/main/antlr4/RuleSet.g4` but if you are not familiar with ANTLR, the following examples will help.

#### Examples

Also, refer to unit test `src/test/java/app/dassana/ruleengine/GrammarTest.java`

Given the following JSON input:
```json  
{  
  "emptyString": "",  
  "emptyObject": {},  
  "applicationArea": 9.2,  
  "number": 42,  
  "negativeNumber": -42,  
  "landUseCodes": "GH,GX",  
  "status": "approved",  
  "organic": false,  
  "array": [  
    {  
      "ProductFields": {  
        "aws/securityhub/ProductName": "Security Hub"  
  }  
    },  
  {  
      "ProductFields": {  
        "aws/securityhub/ProductName": "foo"  
  }  
    }  
  ],  
  "options": [  
    {  
      "code": "AB1",  
  "area": 3  
  },  
  {  
      "code": "AB8",  
  "area": 2  
  }  
  ]  
} 
```  

the simplest JSON rule is the one that refers to a parent level key e.g. `status` or `organic` :

`status is approved` this will evaluate to true.

As mentioned above, for generic operators, you do not provide value (doesn't make sense either) e.g :  
`foo exists`

In JSON Path, for parent level keys, the `$` is optional so the following rules are equivalent :  
`$.status is approved` and `status is approved`

For anything slightly more complicated, the JSON path must be double-quoted. The format is - `"<jsonPath>" operator value`

Take for example the rule `"$.options[?(@.code contains 'AB1')].area" exists`. As you can see, the entire json path is quoted with double quotes.

What if, within the json path you have a double quote? Well, you escape it like this- `"$.options[?(@.code contains \"x\")].area" exists`

When the library parses it, it sends the following to the JSON path engine- `$.options[?(@.code contains "x")].area`

Note that you do *not* have to escape a single quote. As such, following is a valid rule :`"$.options[?(@.code contains 'x')].area" exists`. Notice how we are not escaping the single quote around `x`.

## Dev Notes
For local development/testing, you can use the maven command
`mvn install -DskipTests -Dmaven.javadoc.skip -Dgpg.skip`

### TODO:
