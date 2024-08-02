package com.example.atiperatask.model.response;

import com.example.atiperatask.model.wrapper.RepositoryWrapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetUserReposResponse {
    private List<RepositoryWrapper> repos;
}
