//package com.intellij.prettierjs;

import com.intellij.codeInsight.actions.FileTreeIterator;
import com.intellij.codeInsight.actions.VcsFacade;
import com.intellij.codeInsight.hint.HintManager;
import com.intellij.codeInsight.hint.HintManagerImpl;
import com.intellij.codeInsight.hint.HintUtil;
import com.intellij.codeStyle.AbstractConvertLineSeparatorsAction;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.process.ProcessOutput;
import com.intellij.execution.testframework.autotest.ToggleAutoTestAction;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationListener;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.application.ReadAction;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.ex.util.EditorScrollingPositionKeeper;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.ThrowableComputable;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.ReadonlyStatusHandler;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.util.PsiUtilCore;
import com.intellij.ui.HyperlinkAdapter;
import com.intellij.ui.LightweightHint;
import com.intellij.util.ArrayUtil;
import com.intellij.util.LineSeparator;
import com.intellij.util.NullableFunction;
import com.intellij.util.SmartList;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.text.SemVer;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class ExampleAction extends AnAction implements DumbAware {
//    private static final @NotNull
//    Logger LOG = Logger.getInstance(ReformatWithPrettierAction.class);
//    private static final long EDT_TIMEOUT_MS = 2000;

//    private final ErrorHandler myErrorHandler;

//    public ReformatWithPrettierAction(@NotNull ErrorHandler errorHandler) {
//        myErrorHandler = errorHandler;
//    }

//    public ReformatWithPrettierAction() {
//        this(ErrorHandler.DEFAULT);
//    }

//    @Override
//    public void update(@NotNull AnActionEvent e) {
//        Project project = e.getProject();
//        if (project == null) {
//            e.getPresentation().setEnabledAndVisible(false);
//            return;
//        }
//        NodePackage nodePackage = PrettierConfiguration.getInstance(project).getPackage();
//        e.getPresentation().setEnabledAndVisible(!nodePackage.isEmptyPath() && isAcceptableFileContext(e));
//    }

    //    private static boolean isAcceptableFileContext(@NotNull AnActionEvent e) {
//        Editor editor = e.getData(CommonDataKeys.EDITOR);
//        if (editor != null) {
//            return true;
//        }
//        VirtualFile[] virtualFiles = e.getData(CommonDataKeys.VIRTUAL_FILE_ARRAY);
//        return !ArrayUtil.isEmpty(virtualFiles);
//    }
//
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) {
            return;
        }
        new ToggleAutoTestAction().setSelected(e, true);
    }

}
