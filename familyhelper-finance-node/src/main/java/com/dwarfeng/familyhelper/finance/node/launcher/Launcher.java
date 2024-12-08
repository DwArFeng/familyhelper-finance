package com.dwarfeng.familyhelper.finance.node.launcher;

import com.dwarfeng.familyhelper.finance.node.handler.LauncherSettingHandler;
import com.dwarfeng.familyhelper.finance.stack.service.RemindDriveQosService;
import com.dwarfeng.familyhelper.finance.stack.service.RemindDriverSupportMaintainService;
import com.dwarfeng.familyhelper.finance.stack.service.ResetQosService;
import com.dwarfeng.springterminator.sdk.util.ApplicationUtil;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.Date;

/**
 * 程序启动器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class Launcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(Launcher.class);

    public static void main(String[] args) {
        ApplicationUtil.launch(new String[]{
                "classpath:spring/application-context*.xml",
                "file:opt/opt*.xml",
                "file:optext/opt*.xml"
        }, ctx -> {
            // 根据启动器设置处理器的设置，选择性重置路提醒驱动。
            mayResetRemindDriver(ctx);

            // 根据启动器设置处理器的设置，选择性上线提醒驱动服务。
            mayOnlineRemindDrive(ctx);
            // 根据启动器设置处理器的设置，选择性启动提醒驱动服务。
            mayStartRemindDrive(ctx);

            // 根据启动器设置处理器的设置，选择性启动重置服务。
            mayStartReset(ctx);
        });
    }

    private static void mayResetRemindDriver(ApplicationContext ctx) {
        // 获取启动器设置处理器，用于获取启动器设置，并按照设置选择性执行功能。
        LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);

        // 判断是否重置路提醒驱动支持，并按条件执行重置操作。
        if (launcherSettingHandler.isResetRemindDriverSupport()) {
            LOGGER.info("重置路提醒驱动支持...");
            RemindDriverSupportMaintainService maintainService = ctx.getBean(RemindDriverSupportMaintainService.class);
            try {
                maintainService.reset();
            } catch (ServiceException e) {
                LOGGER.warn("路提醒驱动支持重置失败，异常信息如下", e);
            }
        }
    }

    private static void mayOnlineRemindDrive(ApplicationContext ctx) {
        // 获取启动器设置处理器，用于获取启动器设置，并按照设置选择性执行功能。
        LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);

        // 获取程序中的 ThreadPoolTaskScheduler，用于处理计划任务。
        ThreadPoolTaskScheduler scheduler = ctx.getBean(ThreadPoolTaskScheduler.class);

        // 获取提醒驱动 QOS 服务。
        RemindDriveQosService remindDriveQosService = ctx.getBean(RemindDriveQosService.class);

        // 判断提醒驱动处理器是否上线提醒驱动服务，并按条件执行不同的操作。
        long onlineRemindDriveDelay = launcherSettingHandler.getOnlineRemindDriveDelay();
        if (onlineRemindDriveDelay == 0) {
            LOGGER.info("立即上线提醒驱动服务...");
            try {
                remindDriveQosService.online();
            } catch (ServiceException e) {
                LOGGER.error("无法上线提醒驱动服务，异常原因如下", e);
            }
        } else if (onlineRemindDriveDelay > 0) {
            LOGGER.info("{} 毫秒后上线提醒驱动服务...", onlineRemindDriveDelay);
            scheduler.schedule(
                    () -> {
                        LOGGER.info("上线提醒驱动服务...");
                        try {
                            remindDriveQosService.online();
                        } catch (ServiceException e) {
                            LOGGER.error("无法上线提醒驱动服务，异常原因如下", e);
                        }
                    },
                    new Date(System.currentTimeMillis() + onlineRemindDriveDelay)
            );
        }
    }

    private static void mayStartRemindDrive(ApplicationContext ctx) {
        // 获取启动器设置处理器，用于获取启动器设置，并按照设置选择性执行功能。
        LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);

        // 获取程序中的 ThreadPoolTaskScheduler，用于处理计划任务。
        ThreadPoolTaskScheduler scheduler = ctx.getBean(ThreadPoolTaskScheduler.class);

        // 获取提醒驱动 QOS 服务。
        RemindDriveQosService remindDriveQosService = ctx.getBean(RemindDriveQosService.class);

        // 判断提醒驱动处理器是否启动提醒驱动服务，并按条件执行不同的操作。
        long startRemindDriveDelay = launcherSettingHandler.getStartRemindDriveDelay();
        if (startRemindDriveDelay == 0) {
            LOGGER.info("立即启动提醒驱动服务...");
            try {
                remindDriveQosService.start();
            } catch (ServiceException e) {
                LOGGER.error("无法启动提醒驱动服务，异常原因如下", e);
            }
        } else if (startRemindDriveDelay > 0) {
            LOGGER.info("{} 毫秒后启动提醒驱动服务...", startRemindDriveDelay);
            scheduler.schedule(
                    () -> {
                        LOGGER.info("启动提醒驱动服务...");
                        try {
                            remindDriveQosService.start();
                        } catch (ServiceException e) {
                            LOGGER.error("无法启动提醒驱动服务，异常原因如下", e);
                        }
                    },
                    new Date(System.currentTimeMillis() + startRemindDriveDelay)
            );
        }
    }

    private static void mayStartReset(ApplicationContext ctx) {
        // 获取启动器设置处理器，用于获取启动器设置，并按照设置选择性执行功能。
        LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);

        // 获取程序中的 ThreadPoolTaskScheduler，用于处理计划任务。
        ThreadPoolTaskScheduler scheduler = ctx.getBean(ThreadPoolTaskScheduler.class);

        // 获取重置 QOS 服务。
        ResetQosService resetQosService = ctx.getBean(ResetQosService.class);

        // 判断重置处理器是否启动重置服务，并按条件执行不同的操作。
        long startResetDelay = launcherSettingHandler.getStartResetDelay();
        if (startResetDelay == 0) {
            LOGGER.info("立即启动重置服务...");
            try {
                resetQosService.start();
            } catch (ServiceException e) {
                LOGGER.error("无法启动重置服务，异常原因如下", e);
            }
        } else if (startResetDelay > 0) {
            LOGGER.info("{} 毫秒后启动重置服务...", startResetDelay);
            scheduler.schedule(
                    () -> {
                        LOGGER.info("启动重置服务...");
                        try {
                            resetQosService.start();
                        } catch (ServiceException e) {
                            LOGGER.error("无法启动重置服务，异常原因如下", e);
                        }
                    },
                    new Date(System.currentTimeMillis() + startResetDelay)
            );
        }
    }
}
