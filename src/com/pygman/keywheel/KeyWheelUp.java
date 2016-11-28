package com.pygman.keywheel;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.ScrollingModel;
import com.intellij.openapi.fileEditor.FileEditorManager;

public class KeyWheelUp extends AnAction {
    private static final Logger LOG = Logger.getInstance(KeyWheelUp.class);
    private static final Integer line = 3;


    @Override
    public void actionPerformed(AnActionEvent e) {
        FileEditorManager fileEditorManager = FileEditorManager.getInstance(e.getProject());
        Editor textEditor = fileEditorManager.getSelectedTextEditor();
        ScrollingModel scrollingModel = textEditor.getScrollingModel();
        System.out.println(scrollingModel.getVerticalScrollOffset());
        scrollingModel.disableAnimation();
        scrollingModel.scrollVertically(scrollingModel.getVerticalScrollOffset() - textEditor.getLineHeight() * line);
        scrollingModel.enableAnimation();
    }
}
