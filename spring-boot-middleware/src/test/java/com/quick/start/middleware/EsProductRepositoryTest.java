package com.quick.start.middleware;

import com.quick.start.middleware.dataobject.ESProductDO;
import com.quick.start.middleware.repository.EsProductRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@SpringBootTest
public class EsProductRepositoryTest {
    @Autowired
    private EsProductRepository esProductRepository;

    @Test // 插入一条记录
    public void testInsert() {
        ESProductDO product = new ESProductDO();
        product.setId(1); // 一般 ES 的 ID 编号，使用 DB 数据对应的编号。这里，先写死
        product.setName("我的测试");
        product.setSellPoint("这是一个测试");
        product.setDescription("我只是一行代码");
        product.setCid(1);
        product.setCategoryName("技术");
        esProductRepository.save(product);
    }

    // 这里要注意，如果使用 save 方法来更新的话，必须是全量字段，否则其它字段会被覆盖。
    // 所以，这里仅仅是作为一个示例。
    @Test // 更新一条记录
    public void testUpdate() {
        ESProductDO product = new ESProductDO();
        product.setId(1);
        product.setCid(2);
        product.setCategoryName("技术-Java");
        esProductRepository.save(product);
    }

    @Test // 根据 ID 编号，删除一条记录
    public void testDelete() {
        esProductRepository.deleteById(1);
    }

    @Test // 根据 ID 编号，查询一条记录
    public void testSelectById() {
        Optional<ESProductDO> userDO = esProductRepository.findById(1);
        System.out.println(userDO.isPresent());
    }

    @Test // 根据 ID 编号数组，查询多条记录
    public void testSelectByIds() {
        Iterable<ESProductDO> users = esProductRepository.findAllById(Arrays.asList(1, 4));
        users.forEach(System.out::println);
    }


    @Test // 根据名字获得一条记录
    public void testFindByName() {
        ESProductDO product = esProductRepository.findByName("我的");
        System.out.println(product);
    }

    @Test // 使用 name 模糊查询，分页返回结果
    public void testFindByNameLike() {
        // 根据情况，是否要制造测试数据
        if (true) {
            testInsert();
        }

        // 创建排序条件
        Sort sort = Sort.by(Sort.Direction.DESC, "id"); // ID 倒序
        // 创建分页条件。
        Pageable pageable = PageRequest.of(0, 10, sort);
        // 执行分页操作
        Page<ESProductDO> page = esProductRepository.findByNameLike("我的", pageable);
        // 打印
        System.out.println(page.getTotalElements());
        System.out.println(page.getTotalPages());
    }

//    @Test
//    public void testSearch() {
//        // 查找分类为 1 + 指定关键字，并且按照 id 升序
//        Page<ESProductDO> page = esProductRepository.search(1, "测试",
//                PageRequest.of(0, 5, Sort.Direction.ASC, "id"));
//        System.out.println(page.getTotalPages());
//
//        // 查找分类为 1 ，并且按照 id 升序
//        page = esProductRepository.search(1, null,
//                PageRequest.of(0, 5, Sort.Direction.ASC, "id"));
//        System.out.println(page.getTotalPages());
//    }

//    @Autowired
//    private ElasticsearchRestTemplate elasticsearchTemplate;
//
//    @Test
//    public void test() {
//        // <1> 创建 ES 搜索条件
//        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder()
//                .withIndices("product");;
//        // <2> 筛选
//        nativeSearchQueryBuilder.withQuery(QueryBuilders.multiMatchQuery("测试",
//                "name", "sellPoint", "categoryName"));
//        // <3> 聚合
//        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("cids").field("cid")); // 商品分类
//        // <4> 执行查询
//        ProductConditionBO condition = elasticsearchTemplate.idsQuery(nativeSearchQueryBuilder.build(), response -> {
//            ProductConditionBO result = new ProductConditionBO();
//            // categoryIds 聚合
//            Aggregation categoryIdsAggregation = response.getAggregations().get("cids");
//            if (categoryIdsAggregation != null) {
//                result.setCategories(new ArrayList<>());
//                for (LongTerms.Bucket bucket  : (((LongTerms) categoryIdsAggregation).getBuckets())) {
//                    result.getCategories().add(new ProductConditionBO.Category().setId(bucket.getKeyAsNumber().intValue()));
//                }
//            }
//            // 返回结果
//            return result;
//        });
//        // <5> 后续遍历 condition.categories 数组，查询商品分类，设置商品分类名。
//        System.out.println();
//    }
}
