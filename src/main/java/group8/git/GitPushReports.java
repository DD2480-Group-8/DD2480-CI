package group8.git;


import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;

import java.io.IOException;

public class GitPushReports {

    private Git git;

    // TODO REMOVE
    public static void main(String[] args) {
        try {
            GitPushReports g = new GitPushReports();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GitPushReports() throws IOException, GitAPIException {
        String localPath = System.getProperty("user.dir");

        Repository localRepo = new FileRepository(localPath + "/.git");
        git = new Git(localRepo);

        AddCommand add = git.add();
        add.addFilepattern("history/").call();

        git.commit().setMessage("New build to add to history").call();

        git.push().call();

    }
}
