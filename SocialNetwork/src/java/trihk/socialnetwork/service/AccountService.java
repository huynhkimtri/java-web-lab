/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trihk.socialnetwork.service;

import trihk.socialnetwork.dao.AccountDAO;
import trihk.socialnetwork.dao.AccountRoleDAO;
import trihk.socialnetwork.entity.Account;
import trihk.socialnetwork.entity.AccountRole;
import trihk.socialnetwork.utils.PasswordEncryptUtils;

/**
 *
 * @author TriHuynh
 */
public class AccountService {

  public boolean checkExistEmail(String email) {
    AccountDAO dao = new AccountDAO();
    Account account = dao.getByEmail(email);
    return account != null;
  }

  public Account login(String email, String password) {
    AccountDAO dao = new AccountDAO();
    Account account = dao.getByEmail(email);
    if (account != null) {
      String encryptPwd = PasswordEncryptUtils.encryptSHA256(password);
      if (account.getEncryptPassword().equals(encryptPwd)) {
        return account;
      }
    }
    return null;
  }

  public Account create(String email, String name, String password, int roleId) {
    Account account = new Account();
    account.setEmail(email.trim());
    account.setName(name.trim());
    account.setEncryptPassword(PasswordEncryptUtils.encryptSHA256(password.trim()));
    AccountRoleDAO roleDao = new AccountRoleDAO();
    AccountRole role = roleDao.getById(roleId);
    account.setRoleId(role);
    AccountDAO dao = new AccountDAO();
    account.setIsActive(Boolean.TRUE);
    return dao.create(account);
  }

  public Account update(String email, String name, String password, int roleId, boolean isActive) {
    AccountDAO dao = new AccountDAO();
    Account account = dao.getByEmail(email);
    if (account != null) {
      String encryptPwd = PasswordEncryptUtils.encryptSHA256(password);
      if (account.getEncryptPassword().equals(encryptPwd)) {
        account.setName(name.trim());
        AccountRoleDAO roleDao = new AccountRoleDAO();
        AccountRole role = roleDao.getById(roleId);
        account.setRoleId(role);
        account.setIsActive(isActive);
        return dao.update(account);
      }
    }
    return null;
  }

  public void delete(String email) {
    AccountDAO dao = new AccountDAO();
    Account acc = dao.getByEmail(email);
    if (acc != null) {
      acc.setIsActive(Boolean.FALSE);
      dao.update(acc);
    }
  }
}
