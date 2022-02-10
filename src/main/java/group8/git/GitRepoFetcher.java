package group8.git;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.TextProgressMonitor;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.FileSystemNotFoundException;
import java.util.Arrays;

/**
 * Clones the specified repo using the jgit API
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
     * Clones a specified repository to a specific destination directory
     * @param destination to clone the repository to
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
