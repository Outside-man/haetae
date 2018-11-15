/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.dal.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.dal.user.model.UserDO;

/**
 * 用户实体仓储服务
 * @author dango.yxm
 * @version : UserRepository.java 2018/11/15 下午1:11 dango.yxm
 */
@Repository
public interface UserDORepo extends JpaRepository<UserDO, String> {
}
