package group8.git;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.TextProgressMonitor;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.FileSystemNotFoundException;
import java.util.Arrays;

/**
 * Fetches a GitHub repository using JGit.
 */
public class GitRepoFetcher {
    String repoUrl;
    String ref;

    /**
     * Constructor for the GitRepoFetcher class.
     * @param repoUrl - the url of the repository
     * @param ref - the full branch name on which the new push has occurred and which we have to clone.
     */
    public GitRepoFetcher(String repoUrl, String ref) {
        this.repoUrl = repoUrl;
        this.ref = ref;
        System.out.println(Arrays.asList(this.ref));
    }

    /**
     * Triggers the instance to fetch the desired repo to a certain location
     * @param destination - the location in which the fetched repo will be stored.
     */
    public void fetchToDestination(File destination) {
        try {
            Git.cloneRepository()
                    .setProgressMonitor(new TextProgressMonitor(new PrintWriter(System.out))) // allows us to see progress of the fetching.
                    .setDirectory(destination)
                    .setURI("https://github.com/DD2480-Group-8/DD2480-CI.git")
                    .setBranchesToClone(Arrays.asList(this.ref))
                    .setBranch(this.ref) // checkout the newly fetched branch.
                    .call();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }
}
