/*
  betahouse.us
  CopyRight (c) 2012 - 2019
 */
package us.betahouse.haetae.controller.locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.betahouse.haetae.serviceimpl.locale.service.LocaleService;

/**
 * @author NathanDai
 * @version :  2019-07-03 21:24 NathanDai
 */

/**
 * 场地接口
 */
@RestController
@RequestMapping(value = "/locale")
public class LocaleController {
    /**
     * 日志实体
     */
    private final Logger LOGGER = LoggerFactory.getLogger(LocaleController.class);

    @Autowired
    private LocaleService localeService;

}
