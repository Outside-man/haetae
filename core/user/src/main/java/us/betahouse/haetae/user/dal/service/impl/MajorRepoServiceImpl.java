/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.betahouse.haetae.user.dal.convert.EntityConverter;
import us.betahouse.haetae.user.dal.model.MajorDO;
import us.betahouse.haetae.user.dal.repo.MajorDORepo;
import us.betahouse.haetae.user.dal.service.MajorRepoService;
import us.betahouse.haetae.user.idfactory.BizIdFactory;
import us.betahouse.haetae.user.model.basic.MajorBO;
import us.betahouse.util.utils.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 专业仓储服务
 *
 * @author dango.yxm
 * @version : MajorRepoServiceImpl.java 2018/11/30 8:48 PM dango.yxm
 */
@Service
public class MajorRepoServiceImpl implements MajorRepoService {
    @Autowired
    private BizIdFactory bizIdFactory;
    @Autowired
    private MajorDORepo majorDORepo;

    @Override
    public void create(MajorBO majorBO) {
        MajorDO majorDO=new MajorDO();
        if(StringUtils.isNotBlank(majorBO.getMajorId())){
            majorDO.setMajorId(majorBO.getMajorId());
        }else{
            majorDO.setMajorId(bizIdFactory.getMajorId());
        }
        assert majorBO.getMajorName()!=null ;
        majorDO.setMajorName(majorBO.getMajorName());
        majorDORepo.save(majorDO);
    }

    @Override
    public List<MajorBO> findAll() {
        return CollectionUtils.toStream(majorDORepo.findAll())
                .filter(Objects::nonNull)
                .map(EntityConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public MajorBO findMajor(String majorId) {
        return EntityConverter.convert(majorDORepo.findByMajorId(majorId));
    }


}
