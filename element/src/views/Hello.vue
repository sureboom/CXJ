<template>
    <section class="index">
        <div class="hello">
            <i class="iconfont icon-xihuan"></i>
            <span>很高兴遇见你(*^_^*)<br>❤️{{page.name}}❤️</span>
        </div>
        <div class="msg">
            <p class="title">通知：</p>
            <ul>
                <li @click="openMsg()"><i class="iconfont icon-flag"></i>元旦放假通知</li>
                <li @click="openMsg()"><i class="iconfont icon-flag"></i>下周例行工作报告会</li>
                <li @click="openMsg()"><i class="iconfont icon-flag"></i>关于打卡请假具体流程</li>
            </ul>
        </div>
    </section>

    
</template>

<script>
export default {
    data(){
        return{
            seach:{
                account:'0',
            },
            page:{}
        }
    },
    created(){
        this.loaddate();
    },
        methods: {
            loaddate(){
            this.seach.account = window.sessionStorage.getItem('empId');
            this.$http.get('/queryEmpInfoById/'+this.seach.account).then(res => {
                this.page = res.data;
            }).catch(err => {
                console.error("无法加载用户数据：", err);
            });
        },
        openMsg() {
            this.$alert('根据《国务院办公厅关于2024年部分节假日安排的通知》精神，兰山区将于2025年1月1日（星期三）进行元旦节放假，不再调休，共1天，放假期间不受理业务。1月2日（星期四）开始正常上班受理业务。望各位同事相互转告，给您带来不便，敬请谅解。', '元旦放假通知', {
                confirmButtonText: '确定'
            })
        }
    }
}
</script>

<style scoped>
.index {
    margin-left: 70px;
}

.hello {
    font-size: 20px;
    color: #726f70;
}

.icon-xihuan {
    font-size: 30px;
    color: #dd6572;
}

.msg .title {
    font-size: 16px;
    color: #000;
    margin-top: 20px;
    margin-left: 10px;
}

.msg ul {
    display: flex;
    flex-direction: column;
    width: 200px;
    overflow: hidden;
}

.msg li {
    margin-top: 10px;
    font-size: 14px;
    color: lightcoral;
    cursor: pointer;
    display: inline-block;
}
</style>
