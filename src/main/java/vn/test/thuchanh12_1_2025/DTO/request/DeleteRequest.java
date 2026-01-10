package vn.test.thuchanh12_1_2025.DTO.request;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DeleteRequest {
    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }
}
