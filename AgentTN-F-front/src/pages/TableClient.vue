<template>
    <div class="row">
      <div class="col-12">
        <card :title="table1.title">
          <div class="table-responsive">
            <base-table :data="tableData"
                        :columns="table1.columns"
                        thead-classes="text-primary">
            </base-table>
          </div>
        </card>
      </div>

    </div>
</template>
<script>
import { BaseTable } from "@/components";
import ClientResource from "../api/client";
const tableColumns = ["Id", "FirstName","LastName", "Email", "Phone","CIN","Balance"];


export default {
  components: {
    BaseTable
  },
  data() {
    return {
      tableData : [],
      clientResource : new ClientResource(),
      table1: {
        title: "Client Table",
        columns: [...tableColumns],
      },
    };
  },
  mounted() {
    this.getClients();
  },
  methods: {
    async getClients(){
      const {data} = await this.clientResource.getAll();
      console.log(data);
      data.forEach(element => {
        const agent = {
          id:element.id,
          firstname:element.firstName,
          lastname:element.lastName,
          balance:element.balance,
          cin:element.cin,
          email:element.email,
          phone:element.phone,
        }
        this.tableData.push(agent);
      });
    }
  },
};
</script>
<style>
</style>
