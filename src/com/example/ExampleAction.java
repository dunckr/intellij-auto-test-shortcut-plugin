package com.example;

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

                // should be able to use the processId rather than loop
                for (Content content : contentManager.getContents()) {
                    if (content != null) {
                        RunContentDescriptor descriptor = content.getUserData(RunContentDescriptor.DESCRIPTOR_KEY);

                        if (descriptor != null) {
                            var isEnabled = AutoTestManager.getInstance(project).isAutoTestEnabled(descriptor);
                            LOG.info("isEnabled");
                            LOG.info((Boolean.toString(isEnabled)));


                            JComponent component = content.getComponent();
                            ExecutionEnvironment environment = LangDataKeys.EXECUTION_ENVIRONMENT.getData(DataManager.getInstance().getDataContext(component));


                            AutoTestManager.getInstance(project).setAutoTestEnabled(descriptor, environment, true);

                            isEnabled = AutoTestManager.getInstance(project).isAutoTestEnabled(descriptor);
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
