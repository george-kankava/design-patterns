package org.example.strategy;

public class Validator {

    private final ValidationStrategy validationStrategy;

    public Validator(ValidationStrategy validationStrategy) {
        this.validationStrategy = validationStrategy;
    }

    public boolean validate(String text) {
        return validationStrategy.execute(text);
    }

    public interface ValidationStrategy {
        boolean execute(String text);
    }

    public static class IsAllLowerCase implements ValidationStrategy {

        @Override
        public boolean execute(String text) {
            return text.matches("[a-z]+");
        }
    }

    public static class IsNumeric implements ValidationStrategy {

        @Override
        public boolean execute(String text) {
            return text.matches("\\d+");
        }
    }

    public static void main(String[] args) {
        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        System.out.println("lowerCaseValidator.validate(\"abc\") : " + lowerCaseValidator.validate("abc"));
        System.out.println("lowerCaseValidator.validate(\"abC\") : " + lowerCaseValidator.validate("abC"));
        Validator numericValidator = new Validator(new IsNumeric());
        System.out.println("numericValidator.validate(\"1283\") : " + numericValidator.validate("1283"));
        System.out.println("numericValidator.validate(\"39sf93\") : " + numericValidator.validate("39sf93"));
        // Using lambda
        Validator lambdaNumericValidator = new Validator(text -> text.matches("\\d+"));
        System.out.println("lambdaNumericValidator.validate(\"123\") : " + lambdaNumericValidator.validate("123"));
        System.out.println("lambdaNumericValidator.validate(\"12g3\") : " + lambdaNumericValidator.validate("12g3"));
    }
}
