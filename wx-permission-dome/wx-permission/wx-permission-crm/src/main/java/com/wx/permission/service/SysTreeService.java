package com.wx.permission.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.wx.commons.utils.ConverterUtils;
import com.wx.permission.dao.SysAclModuleMapper;
import com.wx.permission.dao.SysDeptMapper;
import com.wx.permission.model.SysAcl;
import com.wx.permission.model.SysAclModule;
import com.wx.permission.model.SysDept;
import com.wx.permission.utils.AclInfoUtils;
import com.wx.permission.utils.TreeUtils;
import com.wx.permission.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.wx.core.enums.status.SystemEnumStatus.SYS_STATUS.SYS_STATUS_1;


/**
 * @ClassName SysTreeService
 * @Author wx
 * @Description 树结构业务层
 * @Date 2018-08-20-21:57
 */
@Service
public class SysTreeService {
	@Autowired
	private SysDeptMapper sysDeptMapper;

	@Autowired
	private SysAclModuleMapper sysAclModuleMapper;


	
	/**
	 * @methodName: getSysDeptTree
	 * @author: wx
	 * @description: 获取部门树结构
	 * @param
	 * @date: 2018/8/22
	 * @return: java.util.List<com.wx.mypermission.vo.SysDeptTreeVO>
	 */
	public List<SysDeptTreeVO> getSysDeptTree() {
		List<SysDept> deptAll = sysDeptMapper.getDeptAll();
		if (CollectionUtils.isEmpty(deptAll)){
			return Lists.newArrayList();
		}
		List<SysDeptVO> sysDeptVOList = ConverterUtils.listDateConvert(SysDeptVO.class,deptAll);
		return TreeUtils.getTreeList(sysDeptVOList,SysDeptTreeVO.class);

	}
	
	/**
	 * @methodName: getSysAclModuleTree
	 * @author: wx
	 * @description: 获取权限模块树结构
	 * @param
	 * @date: 2018/9/2
	 * @return: java.util.List<com.wx.mypermission.vo.SysAclModuleTreeVO>
	 */
	public List<SysAclModuleTreeVO> getSysAclModuleTree(){
		List<SysAclModule>   sysAclModuleAll =  sysAclModuleMapper.getAclModuleAll();
		if (CollectionUtils.isEmpty(sysAclModuleAll)){
			return Lists.newArrayList();
		}
		List<SysAclModuleVO> sysAclModuleVOList = ConverterUtils.listDateConvert(SysAclModuleVO.class,sysAclModuleAll);
		return TreeUtils.getTreeList(sysAclModuleVOList,SysAclModuleTreeVO.class);
	}
	
	/**
	 * @methodName: roleTree
	 * @author: wx
	 * @description: 根据角色获取权限模块树形
	 * @param roleId
	 * @date: 2018/9/8
	 * @return: java.util.List<com.wx.mypermission.vo.SysAclModuleTreeVO>
	 */
	public List<SysAclModuleTreeVO> roleTree(int roleId) {
		//获取当前用户的权限
		List<SysAclVO> userAclList = AclInfoUtils.getUserAcl();
		//获取角色对应的权限
		List<SysAclVO> roleAclList = AclInfoUtils.getAclListByRoleId(roleId);
		//获取系统所有权限
		List<SysAcl> allAclList = AclInfoUtils.getSysAclMapper().getAll();

		Set<Integer> userAclIdSet = userAclList.stream().map(sysAcl -> sysAcl.getId()).collect(Collectors.toSet());
		Set<Integer> roleAclIdSet = roleAclList.stream().map(sysAcl -> sysAcl.getId()).collect(Collectors.toSet());

		//装载渲染过后的权限
		List<SysAclVO> sysAclVOList = Lists.newArrayList();
		allAclList.forEach(sysAcl ->{
			SysAclVO sysAclVO = new SysAclVO();
			BeanUtils.copyProperties(sysAcl,sysAclVO);
			sysAclVO.setHasAcl(userAclIdSet.contains(sysAcl.getId()) ? true : false);
			sysAclVO.setChecked(roleAclIdSet.contains(sysAcl.getId()) ? true : false);
			sysAclVOList.add(sysAclVO);
		});
		//获取带权限集合的权限模块
		return aclListToTree(sysAclVOList);
    }
	
    /**
	 * @methodName: userAclTree
	 * @author: wx
	 * @description: 根据用户id获取对应的权限数据
	 * @param userId 用户id
	 * @date: 2018/9/9
	 * @return: java.util.List<com.wx.mypermission.vo.SysAclModuleTreeVO>
	 */
	public List<SysAclModuleTreeVO>  userAclTree(int userId) {
		//获取当前用户的权限
		List<SysAclVO> userAclList = AclInfoUtils.getAclListByUserId(userId);
		//渲染的权限
		userAclList.forEach(sysAclVO ->{
			sysAclVO.setHasAcl(true);
			sysAclVO.setChecked(true);
		});
		//获取带权限集合的权限模块
		return aclListToTree(userAclList);
	}
	
    /**
	 * @methodName: aclListToTree
	 * @author: wx
	 * @description: 获取带权限集合的权限模块
	 * @param sysAclVOList 权限集合
	 * @date: 2018/9/8
	 * @return: java.util.List<com.wx.mypermission.vo.SysAclModuleTreeVO>
	 */
	private List<SysAclModuleTreeVO> aclListToTree(List<SysAclVO> sysAclVOList) {
		if (CollectionUtils.isEmpty(sysAclVOList)) {
			return Lists.newArrayList();
		}
		//获取权限模块树
		List<SysAclModuleTreeVO> aclModuleLevelList = getSysAclModuleTree();

		//权限模块与权限进行分组
		Multimap<Integer, SysAclVO> moduleIdAclMap = ArrayListMultimap.create();
		sysAclVOList.forEach(sysAclVO -> {
			if (Objects.equals(sysAclVO.getStatus(), SYS_STATUS_1.getCode())) {
				moduleIdAclMap.put(sysAclVO.getAclModuleId(), sysAclVO);
			}
		});
		//递归渲染
		bindAclsWithOrder(aclModuleLevelList, moduleIdAclMap);
		return aclModuleLevelList;
	}
	
	/**
	 * @methodName: bindAclsWithOrder
	 * @author: wx
	 * @description: 递归给权限模块添加权限集合
	 * @param aclModuleLevelList 权限模块树形数据
	 * @param moduleIdAclMap     权限模块 - 权限集合 map
	 * @date: 2018/9/8
	 * @return: void
	 */
	private void bindAclsWithOrder(List<SysAclModuleTreeVO> aclModuleLevelList, Multimap<Integer, SysAclVO> moduleIdAclMap) {
		if (CollectionUtils.isEmpty(aclModuleLevelList)) {
			return;
		}
		aclModuleLevelList.forEach(sysAclModuleTreeVO ->{
			List<SysAclVO> aclVOList = (List<SysAclVO>)moduleIdAclMap.get(sysAclModuleTreeVO.getId());
			if (!CollectionUtils.isEmpty(aclVOList)) {
				aclVOList.sort(Comparator.comparingInt(SysAclVO::getSeq));
				sysAclModuleTreeVO.setSysAclVOList(aclVOList);
			}
			bindAclsWithOrder(sysAclModuleTreeVO.getTreeVOList(), moduleIdAclMap);
		});
	}

}
