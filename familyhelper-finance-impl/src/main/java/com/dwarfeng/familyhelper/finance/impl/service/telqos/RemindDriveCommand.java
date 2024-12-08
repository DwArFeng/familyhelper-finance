package com.dwarfeng.familyhelper.finance.impl.service.telqos;

import com.dwarfeng.familyhelper.finance.stack.service.RemindDriveQosService;
import com.dwarfeng.springtelqos.node.config.TelqosCommand;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.stack.command.Context;
import com.dwarfeng.springtelqos.stack.exception.TelqosException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

@TelqosCommand
public class RemindDriveCommand extends CliCommand {

    private static final String COMMAND_OPTION_ONLINE = "online";
    private static final String COMMAND_OPTION_OFFLINE = "offline";
    private static final String COMMAND_OPTION_START = "start";
    private static final String COMMAND_OPTION_STOP = "stop";
    private static final String COMMAND_OPTION_STATUS = "status";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_ONLINE,
            COMMAND_OPTION_OFFLINE,
            COMMAND_OPTION_START,
            COMMAND_OPTION_STOP,
            COMMAND_OPTION_STATUS
    };

    // 指令标识符，系专有术语，忽略相关的拼写检查。
    @SuppressWarnings("SpellCheckingInspection")
    private static final String IDENTITY = "redrive";
    private static final String DESCRIPTION = "驱动处理器操作/查看";

    private static final String CMD_LINE_SYNTAX_ONLINE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_ONLINE);
    private static final String CMD_LINE_SYNTAX_OFFLINE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_OFFLINE);
    private static final String CMD_LINE_SYNTAX_START = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_START);
    private static final String CMD_LINE_SYNTAX_STOP = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_STOP);
    private static final String CMD_LINE_SYNTAX_STATUS = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_STATUS);

    private static final String[] CMD_LINE_ARRAY = new String[]{
            CMD_LINE_SYNTAX_ONLINE,
            CMD_LINE_SYNTAX_OFFLINE,
            CMD_LINE_SYNTAX_START,
            CMD_LINE_SYNTAX_STOP,
            CMD_LINE_SYNTAX_STATUS
    };

    private static final String CMD_LINE_SYNTAX = CommandUtil.syntax(CMD_LINE_ARRAY);

    private final RemindDriveQosService remindDriveQosService;

    public RemindDriveCommand(RemindDriveQosService remindDriveQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.remindDriveQosService = remindDriveQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(Option.builder(COMMAND_OPTION_ONLINE).desc("上线驱动处理器").build());
        list.add(Option.builder(COMMAND_OPTION_OFFLINE).desc("下线驱动处理器").build());
        list.add(Option.builder(COMMAND_OPTION_START).desc("启动驱动处理器").build());
        list.add(Option.builder(COMMAND_OPTION_STOP).desc("停止驱动处理器").build());
        list.add(Option.builder(COMMAND_OPTION_STATUS).desc("查看驱动处理器状态").build());
        return list;
    }

    @Override
    protected void executeWithCmd(Context context, CommandLine cmd) throws TelqosException {
        try {
            Pair<String, Integer> pair = CommandUtil.analyseCommand(cmd, COMMAND_OPTION_ARRAY);
            if (pair.getRight() != 1) {
                context.sendMessage(CommandUtil.optionMismatchMessage(COMMAND_OPTION_ARRAY));
                context.sendMessage(CMD_LINE_SYNTAX);
                return;
            }
            switch (pair.getLeft()) {
                case COMMAND_OPTION_ONLINE:
                    remindDriveQosService.online();
                    context.sendMessage("驱动处理器已上线!");
                    break;
                case COMMAND_OPTION_OFFLINE:
                    remindDriveQosService.offline();
                    context.sendMessage("驱动处理器已下线!");
                    break;
                case COMMAND_OPTION_START:
                    remindDriveQosService.start();
                    context.sendMessage("驱动处理器已启动!");
                    break;
                case COMMAND_OPTION_STOP:
                    remindDriveQosService.stop();
                    context.sendMessage("驱动处理器已停止!");
                    break;
                case COMMAND_OPTION_STATUS:
                    printStatus(context);
                    break;
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }

    private void printStatus(Context context) throws Exception {
        boolean onlineFlag = remindDriveQosService.isOnline();
        boolean latchHoldingFlag = remindDriveQosService.isLockHolding();
        boolean startedFlag = remindDriveQosService.isStarted();
        boolean workingFlag = remindDriveQosService.isWorking();

        context.sendMessage(String.format(
                "online: %b, latch holding: %b, started: %b, working: %b.",
                onlineFlag, latchHoldingFlag, startedFlag, workingFlag
        ));
    }
}
