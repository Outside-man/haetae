/*
  betahouse.us
  CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.repo.perm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.user.dal.model.perm.PermDO;

import java.util.List;

/**
 * 权限仓储
 *
 * @author dango.yxm
 * @version : PermDORepo.java 2018/11/16 下午7:07 dango.yxm
 */
@Repository
public interface PermDORepo extends JpaRepository<PermDO, Long> {

    /**
     * 通过权限id获取权限
     *
     * @param permIds
     * @return
     */
    List<PermDO> findAllByPermIdIn(List<String> permIds);

    /**
     * 查询权限
     *
     * @param permId
     * @return
     */
    PermDO findByPermId(String permId);

    /**
     * 查询权限
     *
     * @param permType
     * @return
     */
    PermDO findByPermType(String permType);

    /**
     * 查询权限
     *
     * @param permTypes
     * @return
     */
    List<PermDO> findAllByPermTypeIn(List<String> permTypes);

    /**
     * 通过拓展信息查询
     *
     * @param exiInfo
     * @return
     */
    PermDO findByExtInfo(String exiInfo);

    /**
     * 删除权限
     *
     * @param permId
     */
    void deleteByPermId(String permId);

}
