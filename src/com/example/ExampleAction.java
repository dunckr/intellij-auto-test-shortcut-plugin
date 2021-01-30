package com.example;

import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.execution.testframework.autotest.AutoTestManager;
import com.intellij.execution.testframework.autotest.ToggleAutoTestAction;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

public class ExampleAction extends AnAction {
    Logger LOG = Logger.getInstance(ExampleAction.class);

    public ExampleAction() {
        super();
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        LOG.info("WE ARE HERE!!!!!!!!!!!update111");
        try {
            var project = event.getData(PlatformDataKeys.PROJECT);
            Messages.showMessageDialog(project, "Hello from Kotlin!", "Greeting", Messages.getInformationIcon());
            //            Project project = event.getProject();
            //            LOG.info("we have a project");
            //            RunContentDescriptor descriptor = event.getData(LangDataKeys.RUN_CONTENT_DESCRIPTOR);
            //            var isEnabled = AutoTestManager.getInstance(project).isAutoTestEnabled(descriptor);
            //            LOG.info("is enabled???");
            //            LOG.info(String.valueOf(isEnabled));
            //            new ToggleAutoTestAction().setSelected(event, true);
            //            ExecutionEnvironment environment = event.getData(LangDataKeys.EXECUTION_ENVIRONMENT);
            //            if (project != null && descriptor != null && environment != null) {
            //                LOG.debug("we are not null....");
            //                AutoTestManager.getInstance(project).setAutoTestEnabled(descriptor, environment, true);
            //                AutoTestManager.getInstance(project).isAutoTestEnabled();
            //                LOG.debug("completed....");
            //            } else {
            //                LOG.debug("NULL....");
            //            }
            //            LOG.info("done");
        } catch (Exception e) {
            LOG.error("error!!!!!!!!!!!");
            LOG.debug(e);
        }
    }

    @Override
    public void update(AnActionEvent e) {
        Project project = e.getProject();
        LOG.debug("updating");
        e.getPresentation().setEnabledAndVisible(project != null);
    }
}
