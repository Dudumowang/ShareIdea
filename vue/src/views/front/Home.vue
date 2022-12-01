<template>
  <div>

    <div>
      <el-carousel :interval="10000" type="card" height="400px" style="margin-top: 10px; width: 100%">
        <el-carousel-item v-for="item in imgs" :key="item">
          <img :src="item" alt="">
        </el-carousel-item>
      </el-carousel>
    </div>


    <div style="margin: 10px 0">
      <el-row :gutter="10">
        <el-col :span="6">

        </el-col>
      </el-row>

    </div>


  </div>



</template>

<script>
export default {
  name: "Home",
  data () {
    return {
      imgs : [
          'https://picx.zhimg.com/v2-139627556961d50c4f9b27badce0b99e_r.jpg?source=1940ef5c',
          'https://pica.zhimg.com/v2-e4409c6747bec6b9ac5d22e5dc299747_r.jpg?source=1940ef5c',
          'https://pic1.zhimg.com/v2-db0f7831a7eda4eb07f951657fe5a8e0_r.jpg?source=1940ef5c',
          'https://pic1.zhimg.com/v2-44c75e494d08ba339a2012a37f2aa379_r.jpg?source=1940ef5c',
      ]
    }
  }
}
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
</style>