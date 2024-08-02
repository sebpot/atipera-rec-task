package com.example.atiperatask.model.wrapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RepositoryWrapper {
    private String repositoryName;
    private String ownerLogin;
    private List<BranchWrapper> branches;
}
