package vn.test.thuchanh12_1_2025.Common;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
    private boolean success;
    private T data;
    private String message;

    public BaseResponse(T data, String message) {
        this.success = true;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

}
