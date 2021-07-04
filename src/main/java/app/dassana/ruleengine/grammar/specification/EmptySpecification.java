package app.dassana.ruleengine.grammar.specification;

import app.dassana.ruleengine.IJsonPathParser;
import com.jayway.jsonpath.JsonPath;
import io.micronaut.core.util.StringUtils;
import java.util.Map;
import net.minidev.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmptySpecification extends AbstractSpecification {

  private static final Logger logger = LoggerFactory.getLogger(EmptySpecification.class);

  public EmptySpecification(IJsonPathParser jsonPathParser, String jsonPathExpression,
      String value) {
    super(jsonPathParser, jsonPathExpression, value);
  }


  @Override
  public boolean isSatisfiedBy(String jsonData) {
    Object pathValue = JsonPath.read(jsonData, jsonPathExpression);
    dataType dataType = getDataType(pathValue);

    if (dataType.equals(EmptySpecification.dataType.NULL)
        || dataType.equals(EmptySpecification.dataType.BOOL)
        || dataType.equals(EmptySpecification.dataType.NUMBER)) {
      throw new RuntimeException("The JSON path expression cannot return NULL,BOOLEAN or NUMBER data type for this "
          + "operator");

    }

    if (dataType.equals(EmptySpecification.dataType.STRING) && StringUtils.isEmpty((String) pathValue)) {
      return true;
    }

    if (dataType.equals(EmptySpecification.dataType.OBJECT)) {
      Map obj = (Map) pathValue;
      return obj.size() == 0;
    }

    if (dataType.equals(EmptySpecification.dataType.ARRAY)) {
      JSONArray pathValue1 = (JSONArray) pathValue;
      return pathValue1.size() == 0;
    }

    return false;
  }

  dataType getDataType(Object o) {

    if (o instanceof JSONArray) {
      return dataType.ARRAY;
    }

    if (o instanceof String) {
      return dataType.STRING;
    }

    if (o instanceof Boolean) {
      return dataType.BOOL;
    }

    if (o instanceof Map) {
      return dataType.OBJECT;
    }

    if (o instanceof Double || o instanceof Integer) {
      return dataType.NUMBER;
    }

    if (o == null) {
      return dataType.NULL;
    }

    throw new RuntimeException("Unable to infer data type from :".concat(o.getClass().getName()));


  }

  enum dataType {
    OBJECT, STRING, NUMBER, BOOL, ARRAY, NULL
  }


}
