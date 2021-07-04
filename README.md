

# Introduction

This library provides a simply way to match any given JSON against a set of rules. These rules are expressed in the form of

```
 <jsonpath> <operator> <value>
```
Each valid JSON Rule returns either a `True` or `False` output.

For example, given a json:

```json
{
  "name" : "bond"  
}
```

The JSON rule `$.name is bond` will match (i.e. the library will return `True`).

The JSON rule will be parsed as following-

`path` :`$.name`

`operator`: `is`

`value`:`bond`


The value can be optional in the case of generic operators (e.g. `$.name exists`).


---

## Examples:


JSON Input:

```json
{
  "nullValue": null,
  "name":"bond",
  "names": [
    "foo",
    "bar"
  ],
  "emptyString": "",
  "emptyObject": {},
  "emptyArray": [],
  "double": 9.2,
  "number": 42,
  "options": [
    {
      "code": "foo",
      "area": 3
    },
    {
      "code": "bar",
      "area": 2
    }
  ]
}
```


|Rule  | Is Rule Valid? |Evaluation Result/Note
|--|--|--|  
| `$.names exists` |yes  | True
| `$.foo exists` |yes  | False
| `$.foo does not exist` |yes  | True
| `not $.foo exists` |yes  | True
| `emptyString is empty` |yes  | True
| `$.emptyObject is empty` |yes  | True. For root level keys, `$` is optional.
| `$.number is empty` |no | $.number points to a number but `is empty` operator is not supported for numbers.
| `$.number greater than 1` |Yes | True
| `$.name contains bo` |Yes | True
| `$.name contains X` |Yes | True
| `$.number contains X` |No | $.number is not a string so the operator `contains` is not valid
| `"$.options[?(@.code contains 'x')].area" exists` |Yes | False
| `"$.options[?(@.code contains 'foo')].area" exists` |Yes | True. Note that single quotes do not need to be escaped
| `"$.options[?(@.code contains \"foo\")].area" exists` |Yes | True. Note that double quotes need be escaped.





---

## JSON Path


In JSON Path, for parent level keys, the `$` is optional so the following rules are equivalent :    
`$.status is approved` and `status is approved`. For consistency, we recommend that you always use `$.` to point to the json key.

For anything slightly more complicated, the JSON path must be double-quoted. The format is - `"<jsonPath>" operator value`

Take for example the rule `"$.options[?(@.code contains 'AB1')].area" exists`. As you can see, the entire json path is quoted with double quotes.

What if, within the json path you have a double quote? Well, you escape it like this- `"$.options[?(@.code contains \"x\")].area" exists`

When the library parses it, it sends the following to the JSON path engine- `$.options[?(@.code contains "x")].area`

Note that you do *not* have to escape a single quote. As such, following is a valid rule :`"$.options[?(@.code contains 'x')].area" exists`. Notice how we are not escaping the single quote around `x`.



We use the [jayway library](https://github.com/json-path/JsonPath) for JSON path implementation. For testing purpose, we highly recommend using https://jsonpath.herokuapp.com/ (make sure `Jayway` is selected in the right-side panel)


---

## Operators Reference:

There are two types of operators: Generic and data type specific operators.


### Generic operators:

These operators do not require any "value" in the rule and they work on any given JSON path i.e. you can just provide a json path and the operator: `<json_path> <operator>`

Following three operators are supported:


1. #### `exists`
   This operator checks if the left-hand side (json path) exists or not. e.g. `$.foo exists`

1. #### `does not exist`
   Just the negation of  above. As such the following two rules are same:

       $.foo exists      
       not $.foo does not exist


1. #### `is empty`
   This operator is valid only when the json path points to a "string","object", or an "array". It does not make sense 	to say that a "number" is empty or a boolean or null is empty.  
   Given the following json:

        {
          "names": [
            "foo",
            "bar"
          ],
          "emptyObject": {},
          "age": 42
        }


    Here are some sample rules:  
    |Rule  | isValid |Evaluation Result  
    |--|--|--|  
    | $.age is empty |No  | Error: `$.age` points to a number  
    | $.names is empty |yes  | False  
    | $.emptyObject |yes  | True  
  		  
---
### Data type based operators:

#### String operators:

1. `contains`

1. `is`

#### Numeric operators

1. `greater than`


A lot many other operators will be made available in future releases.

---

## Dev Notes:

The grammar of a JSON rule can be found at `src/main/antlr4/RuleSet.g4`    
The original code can be found [here](https://github.com/stevenalexander/rules-evaluator). The grammar has been significantly changed allowing for easier addition of rule operators.