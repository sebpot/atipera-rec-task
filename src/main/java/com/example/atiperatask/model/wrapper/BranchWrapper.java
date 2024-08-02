package com.example.atiperatask.model.wrapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchWrapper {
    private String branchName;
    private String lastCommitSha;
}
