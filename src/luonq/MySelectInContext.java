package luonq;

import com.intellij.ide.FileEditorProvider;
import com.intellij.ide.SelectInContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MySelectInContext implements SelectInContext {
    @NotNull
    private final PsiFile myPsiFile;
    @Nullable
    private final Editor myEditor;
    @Nullable
    private final Project myProject;

    public MySelectInContext(@NotNull PsiFile psiFile, @Nullable Editor editor, Project myProject) {
        this.myPsiFile = psiFile;
        this.myEditor = editor;
        this.myProject = myProject;
    }

    @NotNull
    public Project getProject() {
        Project var10000 = this.myProject;
        if (this.myProject == null) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", new Object[]{"luonq/MySelectInContext", "getProject"}));
        } else {
            return var10000;
        }
    }

    @NotNull
    private PsiFile getPsiFile() {
        PsiFile var10000 = this.myPsiFile;
        if (this.myPsiFile == null) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", new Object[]{"luonq/MySelectInContext", "getPsiFile"}));
        } else {
            return var10000;
        }
    }

    @NotNull
    public FileEditorProvider getFileEditorProvider() {
        return null;
    }

    @NotNull
    private PsiElement getPsiElement() {
        Object e = null;
        if (this.myEditor != null) {
            int offset = this.myEditor.getCaretModel().getOffset();
            PsiDocumentManager.getInstance(this.myProject).commitAllDocuments();
            e = this.getPsiFile().findElementAt(offset);
        }

        if (e == null) {
            e = this.getPsiFile();
        }

        return (PsiElement) e;
    }

    @NotNull
    public VirtualFile getVirtualFile() {
        return this.getPsiFile().getVirtualFile();
    }

    public Object getSelectorInFile() {
        return this.getPsiElement();
    }
}
