package kr.nomadlab.mentors.exception;

public enum ExceptionCode {
    PAYMENT_NOT_FOUND("Payment not found"),
    PAYMENT_AMOUNT_EXP("Payment amount exceeded"),
    ALREADY_APPROVED("Payment already approved");
    // 다른 예외 코드들을 필요에 따라 추가

    private final String message;

    ExceptionCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    }