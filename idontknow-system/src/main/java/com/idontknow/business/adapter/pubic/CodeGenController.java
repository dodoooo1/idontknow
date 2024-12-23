package com.idontknow.business.adapter.pubic;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.idontknow.business.codegen.ClassInfo;
import com.idontknow.business.codegen.FreemarkerTool;
import com.idontknow.business.codegen.TableParseUtil;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/tool/codegen")
public class CodeGenController {
    private static final Logger logger = LoggerFactory.getLogger(CodeGenController.class);

    @Autowired
    private Configuration freemarkerConfig;

    @RequestMapping
    public String index(Model model) {
        return "tool/codegen";
    }

    @RequestMapping("/genCode")
    @ResponseBody
    public ResponseEntity codeGenerate(String tableSql) {

        try {
            if (Strings.isNullOrEmpty(tableSql)) {
                HashMap<String, String> map = Maps.newHashMap();
                map.put("msg", "结构信息不可为空");
                return ResponseEntity.badRequest().body(map);
            }

            // parse table
            ClassInfo classInfo = TableParseUtil.processTableIntoClassInfo(tableSql);

            // code genarete
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("classInfo", classInfo);

            // result
            Map<String, String> result = new HashMap<String, String>();

            result.put("controller_code", FreemarkerTool.processString(freemarkerConfig, "templates/controller.ftl", params));
            /*result.put("service_code", FreemarkerTool.processString(freemarkerConfig, "tool/codegen-module/service.ftl", params));
            result.put("service_impl_code", FreemarkerTool.processString(freemarkerConfig, "tool/codegen-module/service_impl.ftl", params));
            result.put("mapper_code", FreemarkerTool.processString(freemarkerConfig, "tool/codegen-module/mapper.ftl", params));
            result.put("mapper_xml_code", FreemarkerTool.processString(freemarkerConfig, "tool/codegen-module/mapper_xml.ftl", params));
            */
            result.put("entity_code", FreemarkerTool.processString(freemarkerConfig, "templates/entity.ftl", params));

            // 计算,生成代码行数
            int lineNum = 0;
            for (Map.Entry<String, String> item : result.entrySet()) {
                if (item.getValue() != null) {
                    lineNum += countMatches(item.getValue(), "\n");
                }
            }
            logger.info("genCode lineNum：{}", lineNum);

            return ResponseEntity.ok(result);
        } catch (IOException | TemplateException e) {
            logger.error(e.getMessage(), e);
            Map map = new HashMap<String, String>();
            map.put("msg", "表结构解析失败");
            return ResponseEntity.badRequest().body(map);
        }

    }

    /**
     * Count matches
     *
     * <p>Counts how many times the substring appears in the larger string.</p>
     *
     * <p>A {@code null} or empty ("") String input returns {@code 0}.</p>
     *
     * <pre>
     * StringUtils.countMatches(null, *)       = 0
     * StringUtils.countMatches("", *)         = 0
     * StringUtils.countMatches("abba", null)  = 0
     * StringUtils.countMatches("abba", "")    = 0
     * StringUtils.countMatches("abba", "a")   = 2
     * StringUtils.countMatches("abba", "ab")  = 1
     * StringUtils.countMatches("abba", "xxx") = 0
     * </pre>
     *
     * @param str the CharSequence to check, may be null
     * @param sub the substring to count, may be null
     * @return the number of occurrences, 0 if either CharSequence is {@code null}
     */
    public static int countMatches(String str, String sub) {
        if (Strings.isNullOrEmpty(str) || Strings.isNullOrEmpty(sub)) {
            return 0;
        }
        int count = 0;
        int idx = 0;
        while ((idx = str.indexOf(sub, idx)) != -1) {
            count++;
            idx += sub.length();
        }
        return count;
    }


}
