/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.user.dal.model.UserInfoDO;

/**
 * 用户信息仓储
 *
 * @author dango.yxm
 * @version : UserInfoDORepo.java 2018/11/16 下午7:15 dango.yxm
 */
@Repository
public interface UserInfoDORepo extends JpaRepository<UserInfoDO, Long> {
}
