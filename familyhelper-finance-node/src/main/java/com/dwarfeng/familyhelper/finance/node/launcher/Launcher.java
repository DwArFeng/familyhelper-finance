package com.dwarfeng.familyhelper.finance.node.launcher;

import com.dwarfeng.familyhelper.finance.node.handler.LauncherSettingHandler;
import com.dwarfeng.familyhelper.finance.stack.service.RemindDriveQosService;
import com.dwarfeng.familyhelper.finance.stack.service.RemindDriverSupportMaintainService;
import com.dwarfeng.springterminator.sdk.util.ApplicationUtil;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
            LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);

            // 是否重置提醒驱动器支持。
            if (launcherSettingHandler.isResetRemindDriverSupport()) {
                LOGGER.info("重置提醒驱动器支持...");
                RemindDriverSupportMaintainService maintainService = ctx.getBean(
                        RemindDriverSupportMaintainService.class
                );
                try {
                    maintainService.reset();
                } catch (ServiceException e) {
                    LOGGER.warn("提醒驱动器支持重置失败，异常信息如下", e);
                }
            }

            // 拿出程序中的 ThreadPoolTaskScheduler，用于处理计划任务。
            ThreadPoolTaskScheduler scheduler = ctx.getBean(ThreadPoolTaskScheduler.class);

            // 处理提醒驱动处理器的启动选项。
            RemindDriveQosService remindDriveQosService = ctx.getBean(RemindDriveQosService.class);
            // 提醒驱动处理器是否上线提醒驱动服务。
            long onlineDriveDelay = launcherSettingHandler.getOnlineRemindDriveDelay();
            if (onlineDriveDelay == 0) {
                LOGGER.info("立即上线提醒驱动服务...");
                try {
                    remindDriveQosService.online();
                } catch (ServiceException e) {
                    LOGGER.error("无法上线提醒驱动服务，异常原因如下", e);
                }
            } else if (onlineDriveDelay > 0) {
                LOGGER.info(onlineDriveDelay + " 毫秒后上线提醒驱动服务...");
                scheduler.schedule(
                        () -> {
                            LOGGER.info("上线提醒驱动服务...");
                            try {
                                remindDriveQosService.online();
                            } catch (ServiceException e) {
                                LOGGER.error("无法上线提醒驱动服务，异常原因如下", e);
                            }
                        },
                        new Date(System.currentTimeMillis() + onlineDriveDelay)
                );
            }
            // 提醒驱动处理器是否启动提醒驱动服务。
            long enableDriveDelay = launcherSettingHandler.getStartRemindDriveDelay();
            if (enableDriveDelay == 0) {
                LOGGER.info("立即启动提醒驱动服务...");
                try {
                    remindDriveQosService.online();
                } catch (ServiceException e) {
                    LOGGER.error("无法启动提醒驱动服务，异常原因如下", e);
                }
            } else if (enableDriveDelay > 0) {
                LOGGER.info(enableDriveDelay + " 毫秒后启动提醒驱动服务...");
                scheduler.schedule(
                        () -> {
                            LOGGER.info("启动提醒驱动服务...");
                            try {
                                remindDriveQosService.start();
                            } catch (ServiceException e) {
                                LOGGER.error("无法启动提醒驱动服务，异常原因如下", e);
                            }
                        },
                        new Date(System.currentTimeMillis() + enableDriveDelay)
                );
            }
        });
    }
}
