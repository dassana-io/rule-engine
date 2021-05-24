package app.dassana.ruleengine.web;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class Response {


  boolean error;
  String errorDetails;
  boolean match;
  String match_key;
  String rule;


    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }

    public boolean isMatch() {
        return match;
    }

    public void setMatch(boolean match) {
        this.match = match;
    }

    public String getMatch_key() {
        return match_key;
    }

    public void setMatch_key(String match_key) {
        this.match_key = match_key;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}
