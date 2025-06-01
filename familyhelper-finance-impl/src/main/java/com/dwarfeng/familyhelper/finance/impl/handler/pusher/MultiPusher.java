package com.dwarfeng.familyhelper.finance.impl.handler.pusher;

import com.dwarfeng.familyhelper.finance.sdk.handler.Pusher;
import com.dwarfeng.familyhelper.finance.sdk.handler.pusher.AbstractPusher;
import com.dwarfeng.familyhelper.finance.stack.bean.dto.RemindInfo;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 同时将消息推送给所有代理的多重推送器。
 *
 * @author DwArFeng
 * @since 1.4.0
 */
@Component
public class MultiPusher extends AbstractPusher {

    public static final String SUPPORT_TYPE = "multi";

    private static final Logger LOGGER = LoggerFactory.getLogger(MultiPusher.class);
    private static final String EXCEPTION_MESSAGE = "代理推送器推送数据失败，异常信息如下: ";

    private final List<Pusher> pushers;

    @Value("${pusher.multi.delegate_types}")
    private String delegateTypes;

    private final List<Pusher> delegates = new ArrayList<>();

    public MultiPusher(List<Pusher> pushers) {
        super(SUPPORT_TYPE);
        this.pushers = Objects.isNull(pushers) ? Collections.emptyList() : pushers;
    }

    @PostConstruct
    public void init() throws HandlerException {
        StringTokenizer st = new StringTokenizer(delegateTypes, ",");
        while (st.hasMoreTokens()) {
            String delegateType = st.nextToken();
            delegates.add(pushers.stream().filter(p -> p.supportType(delegateType)).findAny()
                    .orElseThrow(() -> new HandlerException("未知的 pusher 类型: " + delegateType)));
        }
    }

    @Override
    public void remindHappened(RemindInfo remindInfo) {
        for (Pusher delegate : delegates) {
            try {
                delegate.remindHappened(remindInfo);
            } catch (Exception e) {
                LOGGER.warn(EXCEPTION_MESSAGE, e);
            }
        }
    }

    @Override
    public void remindDriveReset() {
        for (Pusher delegate : delegates) {
            try {
                delegate.remindDriveReset();
            } catch (Exception e) {
                LOGGER.warn(EXCEPTION_MESSAGE, e);
            }
        }
    }

    @Override
    public String toString() {
        return "MultiPusher{" +
                "pusherType='" + pusherType + '\'' +
                ", delegateTypes='" + delegateTypes + '\'' +
                ", delegates=" + delegates +
                '}';
    }
}
