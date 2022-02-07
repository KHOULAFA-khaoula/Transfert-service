<template>
  <div class="wrapper">
    <side-bar>
      <template slot="links">
        <sidebar-link
          to="/app/dashboard"
          :name="$t('sidebar.dashboard')"
          icon="tim-icons icon-chart-pie-36"
        />
        <!-- <sidebar-link to="/icons" :name="$t('sidebar.icons')" icon="tim-icons icon-atom"/> -->
        <sidebar-link
          to="/app/console"
          :name="$t('sidebar.Console')"
          icon="tim-icons icon-tv-2"
        />
        <sidebar-link
          to="/app/table-client"
          :name="$t('sidebar.tableClient')"
          icon="tim-icons icon-vector"
        />
        <sidebar-link
          to="/app/table-transfert"
          :name="$t('sidebar.tableTransfert')"
          icon="tim-icons icon-delivery-fast"
        />
        <!-- <sidebar-link to="/maps" :name="$t('sidebar.maps')" icon="tim-icons icon-pin"/> -->
        <!-- <sidebar-link to="/notifications" :name="$t('sidebar.notifications')" icon="tim-icons icon-bell-55"/> -->
        <sidebar-link
          to="/app/profile"
          :name="$t('sidebar.userProfile')"
          icon="tim-icons icon-single-02"
        />
        <!-- <sidebar-link to="/table-list" :name="$t('sidebar.tableList')" icon="tim-icons icon-puzzle-10"/> -->
        <!-- <sidebar-link to="/typography" :name="$t('sidebar.typography')" icon="tim-icons icon-align-center"/> -->
        <sidebar-link
          :to="ENG"
          :name="$t('sidebar.rtlSupport')"
          icon="tim-icons icon-world"
        />
        <div class="solde">Solde : {{solde}}</div>
      </template>
    </side-bar>
    <div class="main-panel">
      <top-navbar></top-navbar>

      <dashboard-content @click.native="toggleSidebar"> </dashboard-content>

      <content-footer></content-footer>
    </div>
  </div>
</template>
<style lang="scss"></style>
<script>
import TopNavbar from "./TopNavbar.vue";
import ContentFooter from "./ContentFooter.vue";
import DashboardContent from "./Content.vue";
import MobileMenu from "./MobileMenu";
import AgentResource from "../../api/agent";
import { JwtGetId } from "../../utils/auth";
export default {
  components: {
    TopNavbar,
    ContentFooter,
    DashboardContent,
    MobileMenu
  },
  data() {

    return {
      agentResource:new AgentResource(),
      solde : "",
      ENG: "/dashboard?enableRTL=true"
    };
  },
  mounted(){
    this.getSolde();
  },
  methods: {
    async getSolde(){
      const {data} = await this.agentResource.getByID(JwtGetId());
      console.log(data);
      this.solde = data.solde;
    },
    toggleSidebar() {
      if (this.$sidebar.showSidebar) {
        this.$sidebar.displaySidebar(false);
      }
    },
    switch1() {
      if (ENG) {
        this.ENG = "/dashboard?enableRTL=true";
      } else {
        this.ENG = "/dashboard?enableRTL=false";
      }
    }
  }
};
</script>
<style scoped>
.solde {
  margin-left: 50px;
  font-weight: 600;
  color: rgba(255, 255, 255, 0.8);
  line-height: 30px;
  position: relative;
  display: block;
  height: auto;
  white-space: nowrap;
  margin-top: 100px;
}
</style>
