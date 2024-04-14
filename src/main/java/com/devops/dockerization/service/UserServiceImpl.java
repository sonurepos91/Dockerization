package com.devops.dockerization.service;

import com.devops.dockerization.customException.NoUserExistsException;
import com.devops.dockerization.customException.UserAlreadyExistsException;
import com.devops.dockerization.customException.UserNotFoundException;
import com.devops.dockerization.dto.BankAccountsVo;
import com.devops.dockerization.dto.UserAddressVo;
import com.devops.dockerization.dto.UserVo;
import com.devops.dockerization.model.BankAccount;
import com.devops.dockerization.model.EntityAddress;
import com.devops.dockerization.model.UserEntity;
import com.devops.dockerization.repository.BankAccountRepository;
import com.devops.dockerization.repository.EntityAddressRepository;
import com.devops.dockerization.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private EntityAddressRepository entityAddressRepository;

    @Autowired
    private EntityManagerFactory entityManagerFactory;


    @Override
    @Transactional(readOnly = true)
    public void validateUserRequest (UserVo userDetails) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByFirstNameAndPhoneNo(userDetails.getFirstName(), userDetails.getPhoneNo());
        if (optionalUserEntity.isPresent()) {
            throw new UserAlreadyExistsException("name", userDetails.getFirstName(), "User Already Exists");
        }
    }

    @Override
    @Transactional
    public Long saveUser (UserVo userDetails) {
        Long userId = saveUserEntity(userDetails);
        saveEntityAddress(userId, userDetails.getAddresses());
        saveEntityBankAccounts(userId, userDetails.getBankAccounts());
        return userId;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> getUserDetailsByAge (Integer age) {

        List<UserEntity> userEntities = userRepository.findByAgeGreaterThanEqual(age);
        if (userEntities != null) {
            return userEntities;
        }
        throw new NoUserExistsException("age", String.valueOf(age), "No Users Greater than age " + age);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> getUsersOrderByName (Integer age) {
        List<UserEntity> userEntities = userRepository.findByAgeOrderByLastNameAsc(age);
        if (userEntities == null) {
            throw new UserNotFoundException("age", String.valueOf(age), "No Users Exists With Last Name");
        }
        return userEntities;
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity getUserByFirstNameOrPhoneNo (String firstName, String phoneNo) {
        Optional<UserEntity> entity = userRepository.findByFirstNameOrPhoneNo(firstName, phoneNo);
        if (entity.isPresent()) {
            return entity.get();
        }
        throw new UserNotFoundException("phoneNo", phoneNo, "No User Exist with firstName " + firstName + " phoneNo " + phoneNo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> getAllUsers (String pageNo, String pageSize, String sortBy) {
        PageRequest paging = PageRequest.of(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
        Page<UserEntity> result = userRepository.findAll(paging);
        if (result.hasContent()) {
            return result.stream().toList();
        } else {
            return new ArrayList<>();
        }
    }

    /*
    If We are using EntityManager we have to ourself begin(),persist(),commit() and close the Transaction
    Spring will not provide out of box Transaction Management
    */
    @Override
    public UserEntity getUserDetailsByEntityManager (Long userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
       /* Query query = entityManager.createQuery("Select u from UserEntity u where u.id=?1");
        query.setParameter(1,userId);
        return  (UserEntity) query.getSingleResult();*/

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBankAccountNo("utuy");
        bankAccount.setBankName("hjgjh");
        bankAccount.setUserId(Long.valueOf(1));
        bankAccount.setIfscCode("tuy");
        // Handling of Transactions
        entityManager.getTransaction().begin();
        entityManager.persist(bankAccount);
        entityManager.getTransaction().commit();
        entityManager.close();
        return null;
    }

    @Override
    public UserEntity getUserDetails (Long userId) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if (userEntity.isEmpty()) {
            throw new UserNotFoundException("id", String.valueOf(userId), "User Does Not Exists");
        }
        return userEntity.get();
    }

    private Long saveUserEntity (UserVo userDetails) {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userDetails.getFirstName());
        userEntity.setLastName(userDetails.getLastName());
        userEntity.setAge(userDetails.getAge());
        userEntity.setEmail(userDetails.getEmail());
        userEntity.setPhoneNo(userDetails.getPhoneNo());
        return userRepository.save(userEntity).getId();
    }

    private void saveEntityBankAccounts (Long userId, List<BankAccountsVo> bankAccountsVoList) {
        List<BankAccount> bankAccounts = new ArrayList<>();
        bankAccountsVoList.forEach(bankAccountsVo -> {
            BankAccount bankAccount = new BankAccount();
            bankAccount.setBankAccountNo(bankAccountsVo.getBankAccountNo());
            bankAccount.setBankName(bankAccountsVo.getBankName());
            bankAccount.setUserId(userId);
            bankAccount.setIfscCode(bankAccountsVo.getIfscCode());
            bankAccounts.add(bankAccount);
        });
        bankAccountRepository.saveAll(bankAccounts);
    }

    private void saveEntityAddress (Long userId, List<UserAddressVo> addressVoList) {
        List<EntityAddress> entityAddressList = new ArrayList<>();
        addressVoList.forEach(userAddressVo -> {
            EntityAddress entityAddress = new EntityAddress();
            entityAddress.setUserId(userId);
            entityAddress.setStreet(userAddressVo.getStreet());
            entityAddress.setLocality(userAddressVo.getLocality());
            entityAddress.setCity(userAddressVo.getCity());
            entityAddress.setCountry(userAddressVo.getCountry());
            entityAddressList.add(entityAddress);
        });
        entityAddressRepository.saveAll(entityAddressList);
    }
}
