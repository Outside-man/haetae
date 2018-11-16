/**
 * betahouse.us
 * CopyRight (c) 2012 - 2018
 */
package us.betahouse.haetae.user.dal.repo.perm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import us.betahouse.haetae.user.dal.model.perm.UserDO;


/**
 * 用户实体仓储
 *
 * @author dango.yxm
 * @version : UserRepository.java 2018/11/15 下午1:11 dango.yxm
 */
@Repository
public interface UserDORepo extends JpaRepository<UserDO, Long> {

    /**
     * 通过用户名获取用户实体
     *
     * @param userName
     * @return
     */
    UserDO findByUsername(String userName);

    /**
     * 通过用户id获取
     *
     * @param userId
     * @return
     */
    UserDO findByUserId(String userId);
}
