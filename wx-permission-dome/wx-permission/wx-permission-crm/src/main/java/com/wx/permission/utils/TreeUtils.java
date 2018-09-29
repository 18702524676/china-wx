package com.wx.permission.utils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.wx.commons.utils.BusinessValidatorUtils;
import com.wx.core.exception.BusinessJsonException;
import com.wx.permission.vo.TreeParentVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;

import static com.wx.permission.constant.PermissionConstant.LevelRule.ROOT;
import static com.wx.permission.constant.PermissionConstant.LevelRule.SEGMENTATION;
import static com.wx.permission.enums.PermissionEnumError.TreeEnumError.TREE_ENUM_ERROR_0;


/**
 * @ClassName TreeUtils
 * @Author wx
 * @Description 树形结构工具类
 * @Date 2018-08-21-21:47
 */
public class TreeUtils {


    /**
     * @param treeList 数据源
     * @param treeType 返回树形结构集合类型
     * @methodName: getTreeList
     * @author: wx
     * @description: 获取树形结构数据
     * @date: 2018/8/25
     * @return: java.util.List<T>
     */
    public static <T extends TreeParentVO, V> List<T> getTreeList(List<V> treeList, Class<T> treeType) {
        //验证集合是否为空
        if (CollectionUtils.isEmpty(treeList)) {
            return Lists.newArrayList();
        }
        /** Start 创建装载树形treeVOList与储存Level对应所有部门的levelAclModuleMap */
        List<T> treeVOList = Lists.newArrayList();
        Multimap<String, T> levelAclModuleMap = ArrayListMultimap.create();
        /** End  */

        /** 填充treeList 与 levelAclModuleMap */
        treeList.forEach(source -> {
            try {
                T treeVO = treeType.newInstance();
                BeanUtils.copyProperties(source, treeVO);
                if (Objects.equals(treeVO.getLevel(), ROOT)) {
                    //装载root节点数据
                    treeVOList.add(treeVO);
                } else {
                    //储存Level对应所有节点
                    levelAclModuleMap.put(treeVO.getLevel(), treeVO);
                }
            } catch (Exception e) {
                throw new BusinessJsonException(e.getMessage());
            }
        });
        /** End */


        // sysDeptTreeVOList排序
        treeVOList.sort(Comparator.comparingInt(T::getSeq));
        // 递归装载所有下级数据
        transformDeptTree(treeVOList, ROOT, levelAclModuleMap);
        return treeVOList;
    }

    /**
     * @param parentId 父级id
     * @param level    部门层级
     * @methodName: generateLevel
     * @author: wx
     * @description: 生成树形层级方法
     * @date: 2018/8/19
     * @return: java.lang.String
     */
    public static String generateLevel(String level, int parentId) {
        if (StringUtils.isBlank(level)) {
            return ROOT;
        } else {
            return StringUtils.join(level, SEGMENTATION, parentId);
        }
    }

    /**
     * @methodName: distinctLevel
     * @author: wx
     * @description: 层级去重
     * @param level
     * @date: 2018/9/16
     * @return: java.lang.String
     */
    public static String distinctLevel(String level){
        BusinessValidatorUtils.notBlank(level,TREE_ENUM_ERROR_0.getMessageError());
        String[] levelArray = level.split("\\.");
        Set<String> levelLinkedSet =  Sets.newLinkedHashSet(Lists.newArrayList(levelArray));
        StringJoiner newLevel = new StringJoiner(".");
        levelLinkedSet.forEach(levelId ->{
            newLevel.add(levelId);
        });
        return newLevel.toString();
    }

    /**
     * @methodName: transformDeptTree
     * @param treeVOList        存储root节点树
     * @param level             所有上级id
     * @param levelAclModuleMap 储存Level对应所有节点
     * @author: wx
     * @description: 递归装载所有下级数据私有方法
     * @date: 2018/8/25
     * @return: void
     */
    private static <T extends TreeParentVO> void transformDeptTree(List<T> treeVOList, String level, Multimap<String, T> levelAclModuleMap) {
        treeVOList.forEach(treeVO -> {
            // 拼接下级的Level 当前部门的Level+当前部门id = 下级部门level
            String nextLevel = StringUtils.join(level, SEGMENTATION, treeVO.getId());
            // 获取level对应的部门
            List<T> nextSysDeptTreeVOList = (List<T>) levelAclModuleMap.get(nextLevel);
            // 如果存在，添加下级部门到当前部门的集合里，继续递归执行transformDeptTree
            if (!CollectionUtils.isEmpty(nextSysDeptTreeVOList)) {
                treeVO.setTreeVOList(nextSysDeptTreeVOList);
                // nextSysDeptTreeVOList排序
                nextSysDeptTreeVOList.sort(Comparator.comparingInt(T::getSeq));
                // 递归执行
                transformDeptTree(nextSysDeptTreeVOList, nextLevel, levelAclModuleMap);
            }
        });
    }

}
