<template>
  <div class="row">
    <div class="col-md-8">
      <edit-profile-form :model="model">
      </edit-profile-form>
    </div>
    <div class="col-md-4">
      <user-card :user="user"></user-card>
    </div>
  </div>
</template>
<script>
  import EditProfileForm from './Profile/EditProfileForm';
  import UserCard from './Profile/UserCard'
  import AgentResource from "../api/agent"
  import {JwtGetId} from "../utils/auth"
  export default {
    components: {
      EditProfileForm,
      UserCard
    },
    data() {
      return {
        agentResource:new AgentResource(),
        model: {
          company: 'OAMAYA',
          email: 'mike@email.com',
          username: 'michael23',
          firstName: 'Mike',
          lastName: 'Andrew',
          address: 'Bld Mihail Kogalniceanu, nr. 8 Bl 1, Sc 1, Ap 09',
          city: 'Melbourne',
          country: 'Morocco',
          postal:'40000',
          about: 'Lamborghini Mercy, Your chick she so thirsty, I\'m in that two seat Lambo.',
          solde: 100000
        },
        user: {
          fullName:"",
          title: 'Agent',
          description: `Do not be scared of the truth because we need to restart the human foundation `,
        }
      }
    },
    mounted() {
      this.getAgentById(JwtGetId());

    },
    methods:{
      async getAgentById(id){
        const {data} = await this.agentResource.getByID(id);
        console.log(data);
        this.model.company = data.company;
        this.model.email = data.email;
        this.model.username= "Agent-"+data.agentId;
        this.model.firstName= data.firstName;
        this.model.lastName = data.lastName;
        this.model.address = data.address;
        this.model.city = data.city;
        this.model.country = data.country;
        this.model.postal = data.postal;
        this.model.about = data.about;
        this.model.solde = data.solde;
        this.user.fullName= data.firstName +" "+ data.lastName ;
      }
    }
  }
</script>
<style>
</style>
