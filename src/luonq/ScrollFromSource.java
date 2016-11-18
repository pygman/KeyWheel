package luonq;

import com.intellij.ide.SelectInTarget;
import com.intellij.ide.projectView.ProjectView;
import com.intellij.ide.projectView.impl.AbstractProjectViewPane;
import com.intellij.ide.projectView.impl.ProjectViewImpl;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ScrollFromSource extends AnAction {
    private static final Logger LOG = Logger.getInstance(ScrollFromSource.class);
    private Project myProject;

    public ScrollFromSource() {
        System.out.println("new");
    }

    public void actionPerformed(AnActionEvent e) {
        System.out.println("action");
        this.myProject = e.getProject();
        FileEditorManager fileEditorManager = FileEditorManager.getInstance(this.myProject);
        Editor selectedTextEditor = fileEditorManager.getSelectedTextEditor();
        if (selectedTextEditor != null) {
            this.selectElementAtCaret(selectedTextEditor);
        }
    }

    private void selectElementAtCaret(@NotNull Editor editor) {
        PsiFile file = PsiDocumentManager.getInstance(this.myProject).getPsiFile(editor.getDocument());
        this.scrollFromFile(file, editor);
    }

    private void scrollFromFile(@NotNull PsiFile file, @Nullable Editor editor) {
        MySelectInContext selectInContext = new MySelectInContext(file, editor, this.myProject);
        ProjectViewImpl projectView = (ProjectViewImpl) ProjectView.getInstance(this.myProject);
        AbstractProjectViewPane currentProjectViewPane = projectView.getCurrentProjectViewPane();
        if (currentProjectViewPane != null) {
            SelectInTarget target = currentProjectViewPane.createSelectInTarget();
            if (target != null && target.canSelect(selectInContext)) {
                target.selectIn(selectInContext, false);
            }
        }

    }
}
