package group8.git;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;

public class GitRepoFetcher {
    String repoUrl;

    public GitRepoFetcher(String repoUrl) {
        this.repoUrl = repoUrl;
    }

    public void fetchToDestination(File destination) {
        try {
            Git.cloneRepository()
                    .setURI("https://github.com/DD2480-Group-8/DD2480-CI.git")
                    .setDirectory(destination)
                    .call();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }
}
