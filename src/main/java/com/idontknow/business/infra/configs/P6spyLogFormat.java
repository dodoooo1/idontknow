package com.idontknow.business.infra.configs;

import com.idontknow.business.constants.SymbolConstants;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.springframework.util.StringUtils;

/**
 * @Description: TODO
 * @author: glory
 * @date: 2020年08月04日 14:34
 */
public class P6spyLogFormat implements MessageFormattingStrategy {
    @Override
    public String formatMessage(final int connectionId, final String now, final long elapsed, final String category, final String prepared, final String sql, final String url) {
        return StringUtils.isEmpty(sql) ? null : " Execute SQL：" + sql.replaceAll("[\\s]+", SymbolConstants.SPACE);
    }
}
