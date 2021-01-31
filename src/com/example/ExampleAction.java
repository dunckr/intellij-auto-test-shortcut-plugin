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
import com.intellij.ui.content.Content;
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

//            ExecutionManager executionManager = ExecutionManager.getInstance(project);

//            Content content = RunDashboardManager.getInstance(project).getDashboardContentManager().getSelectedContent();
//            RunContentDescriptor descriptor = RunContentManagerImpl.getRunContentDescriptorByContent(content);

//            JComponent component = content.getComponent();

//            ExecutionEnvironment environment = LangDataKeys.EXECUTION_ENVIRONMENT.getData(DataManager.getInstance().getDataContext());
//            ExecutionEnvironment environment = LangDataKeys.EXECUTION_ENVIRONMENT.getData(DataManager.getInstance().getDataContext(component));


//             RunContentDescriptor descriptor ExecutionEnvironment environment
//            AutoTestManager.getInstance(project).setAutoTestEnabled(descriptor, environment, true);


//            var isEnabled = new ToggleAutoTestAction().isSelected(event);
//
//            LOG.info("ad");
//
////            RunContentDescriptor descriptor = event.getData(LangDataKeys.RUN_CONTENT_DESCRIPTOR);
////            ExecutionEnvironment environment = event.getData(LangDataKeys.EXECUTION_ENVIRONMENT);
//
//
//            for (ProcessHandler processHandler : executionManager.getRunningProcesses()) {
//                processHandler.getUserData()
//
//                LOG.info("ad");
//                processHandler.con
//            }
//
////            ExecutionManager.getInstance(project).contentManager.selectedContent
//
            RunConfiguration[] allConfigurations = RunManager.getInstance(project).getAllConfigurations();
//
////            new RunContentDescriptor().isAc
//
            for (RunConfiguration configuration : allConfigurations) {

                configuration.
//                RunConfiguration configuration = RunConfiguration.DATA_KEY.getData(dataContext);
//                ExecutionEnvironment environment = configuration.getData(LangDataKeys.EXECUTION_ENVIRONMENT);
//                ProgramRunner runner = RunnerRegistry.getInstance().getRunner(DefaultRunExecutor.EXECUTOR_ID, configuration);
//
//                LOG.info("ad");
//
            }


//
//            if (project != null && descriptor != null && environment != null) {
//                LOG.debug("we are not null....");
//                new ToggleAutoTestAction().setSelected(event, true);
//                // AutoTestManager.getInstance(project).setAutoTestEnabled(descriptor, environment, true);
//                // LOG.debug("completed....");
//            } else {
//                LOG.debug("NULL....");
//            }
            LOG.info("done");
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
