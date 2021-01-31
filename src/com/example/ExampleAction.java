package com.example;

import com.intellij.execution.ExecutionManager;
import com.intellij.execution.RunManager;
import com.intellij.execution.RunnerRegistry;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.dashboard.RunDashboardManager;
import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.runners.ProgramRunner;
import com.intellij.execution.testframework.autotest.AutoTestManager;
import com.intellij.execution.testframework.autotest.ToggleAutoTestAction;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.execution.ui.RunContentManagerImpl;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowId;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentManager;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class ExampleAction extends AnAction implements AnAction.TransparentUpdate {
    Logger LOG = Logger.getInstance(ExampleAction.class);

    public ExampleAction() {
        super();
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        LOG.info("WE ARE HERE!!!!!!!!!!!");
        try {
            var project = event.getData(PlatformDataKeys.PROJECT);

            if (project != null) {
                ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);

                ToolWindow window = toolWindowManager.getToolWindow(ToolWindowId.RUN);
                LOG.info("have window");
                ContentManager contentManager = window.getContentManager();
                LOG.info("have contentManager");

                for (Content content : contentManager.getContents()) {
                    if (content != null) {
                        RunContentDescriptor descriptor = content.getUserData(RunContentDescriptor.DESCRIPTOR_KEY);

                        if (descriptor != null) {
                            var isEnabled = AutoTestManager.getInstance(project).isAutoTestEnabled(descriptor);
                            LOG.info("isEnabled");
                            LOG.info((Boolean.toString(isEnabled)));
                        } else {
                            LOG.info("descriptor is null");
                        }
                    } else {
                        LOG.info("content is null");
                    }
                }
            } else {
                LOG.info("project is null");
            }

            LOG.info("done");
        } catch (Exception e) {
            LOG.info("error!!!!!!!!!!!");
            LOG.info(e);
        }
    }

    @Override
    public void update(AnActionEvent e) {
        Project project = e.getProject();
        LOG.debug("update");
        e.getPresentation().setEnabledAndVisible(project != null);
    }
}
