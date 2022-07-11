<template>
  <a-layout>
    <a-layout-content :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }">
      <p>
        <a-form layout="inline" :model="param">
          <a-form-item>
            <a-button type="primary" @click="add()">
              新增
            </a-button>
          </a-form-item>
        </a-form>
      </p>
      <a-table
          :columns="columns"
          :row-key="record => record.id"
          :data-source="ebooks"
          :pagination="pagination"
          :loading="loading"
          @change="handleTableChange"
      >
        <template #cover="{ text: cover }">
          <img v-if="cover" :src="cover" alt="avatar" />
        </template>
        <template v-slot:action="{ text,record }">
          <a-space size="small">
            <a-button type="primary" @click="edit(record)">
              编辑
            </a-button>
            <a-button type="danger">
              删除
            </a-button>
          </a-space>
        </template >
      </a-table>
    </a-layout-content>
  </a-layout>

  <a-modal
      v-model:visible="modalVisible"
      title="电子书表单"
      :confirm-loading="modalLoading"
      @ok="handleModalOk"
  >
    <a-form :model="ebook" :label-col="{span: 6}">
      <a-form-item label="封面">
        <a-input v-model:value="ebook.cover" />
      </a-form-item>
      <a-form-item label="名称">
        <a-input v-model:value="ebook.name" />
      </a-form-item>
      <a-form-item label="分类一">
        <a-input v-model:value="ebook.category1Id" />
      </a-form-item>
      <a-form-item label="分类二">
        <a-input v-model:value="ebook.category2Id" />
      </a-form-item>
      <a-form-item label="描述">
        <a-input v-model:value="ebook.description" />
      </a-form-item>

    </a-form>
  </a-modal>

</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue';
import axios from 'axios';

export default defineComponent({
  name: 'AdminEbook',
  setup() {
    const ebooks = ref();
    const pagination = ref({
      current: 1,
      pageSize: 3,
      total: 0
    });
    const loading = ref(false);

    const columns = [
      {
        title: '封面',
        dataIndex: 'cover',
        slots: { customRender: 'cover' }
      },
      {
        title: '名称',
        dataIndex: 'name'
      },
      {
        title: '分类',
        slots: { customRender: 'category' }
      },
      {
        title: '文档数',
        dataIndex: 'docCount'
      },
      {
        title: '阅读数',
        dataIndex: 'viewCount'
      },
      {
        title: '点赞数',
        dataIndex: 'voteCount'
      },
      {
        title: 'Action',
        key: 'action',
        slots: { customRender: 'action' }
      }
    ];

    /**
     * 数据查询
     */
    const handleQuery=(param:any)=>{
      loading.value=true;
      axios.get("/ebook/list", {
        params: {
          page: param.page,
          size: param.size
        }
      }).then((response)=>{
        loading.value=false;
        const date=response.data;
        ebooks.value=date.date.list;

        //重置分页按钮
        pagination.value.current=param.page;
        pagination.value.total=date.date.total;
      })
    }

    /**
     * 表格点击页码时触发
     */
    const handleTableChange =(pagination:any)=>{
      console.log("看看自带的分页参数都有啥"+pagination);
      handleQuery({
        page:pagination.current,
        size:pagination.pageSize
      });
    };

    //---------表单--------------
    const modalVisible = ref<boolean>(false);
    const modalLoading = ref<boolean>(false);
    const ebook = ref({});

    /**
     * 编辑
     */
    const edit = (record: any) => {
      modalVisible.value = true;
      ebook.value=record;
    };

    /**
     * 新增
     */
    const add = () => {
      modalVisible.value = true;
      ebook.value= {};
    };

    const handleModalOk = () => {
      modalLoading.value = true;  //表示有加载效果
      axios.post("/ebook/save", ebook.value).then((response)=>{
        const date=response.data;  //data等于CommonResp
        if(date.success){
          modalVisible.value = false;
          modalLoading.value = false;

          //重新加载列表
          handleQuery({
            page: pagination.value.current,
            size: pagination.value.pageSize,
          })
        }
      });
    };

    //刚上来的页面渲染，从第一页开始
    onMounted(() => {
      handleQuery({
        page:1,
        size:pagination.value.pageSize
      });
    });

    return {
      ebooks,
      pagination,
      columns,
      loading,
      handleTableChange,

      //表单
      modalVisible,
      modalLoading,
      edit,
      add,
      handleModalOk,
      ebook
    }
  }
});
</script>

<style scoped>
img {
  width: 50px;
  height: 50px;
}
</style>
