/*
 * betahouse.us
 * Copyright (c) 2012 - 2019
 */

package us.betahouse.haetae.controller.locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author NathanDai
 * @version :  2019-07-04 22:10 NathanDai
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/localearea")
public class LocaleAreaController {
    /**
     * 日志实体
     */
    private final Logger LOGGER = LoggerFactory.getLogger(LocaleAreaController.class);

}
