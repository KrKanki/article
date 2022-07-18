
package com.easypick.article.repository;

import com.easypick.article.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long>{
}