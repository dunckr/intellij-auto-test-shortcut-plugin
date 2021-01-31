package com;

import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.testframework.autotest.AutoTestManager;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowId;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentManager;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class AutoTestShortcutAction extends AnAction {
    Logger LOG = Logger.getInstance(AutoTestShortcutAction.class);

    public AutoTestShortcutAction() {
        super();
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        try {
            var project = event.getData(PlatformDataKeys.PROJECT);
            ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);
            ToolWindow window = toolWindowManager.getToolWindow(ToolWindowId.RUN);
            ContentManager contentManager = window.getContentManager();

            // TODO should be able to use the processId rather than loop
            for (Content content : contentManager.getContents()) {
                RunContentDescriptor descriptor = content.getUserData(RunContentDescriptor.DESCRIPTOR_KEY);

                var isEnabled = AutoTestManager.getInstance(project).isAutoTestEnabled(descriptor);
                if (isEnabled) {
                    return;
                }
                JComponent component = content.getComponent();
                ExecutionEnvironment environment = LangDataKeys.EXECUTION_ENVIRONMENT.getData(DataManager.getInstance().getDataContext(component));
                AutoTestManager.getInstance(project).setAutoTestEnabled(descriptor, environment, true);
            }
        } catch (Exception error) {
            LOG.info("Error");
        }
    }

    @Override
    public void update(AnActionEvent event) {
        Project project = event.getProject();
        event.getPresentation().setEnabledAndVisible(project != null);
    }
}
