'use strict';

App.factory('CompanyService', ['$http', '$q', function($http, $q){

	return {
		
			loadAllCompanies: function() {
					return $http.get('/company')
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while loading companies');
										return $q.reject(errResponse);
									}
							);
			},

		    loadCompany: function(companyID) {
					return $http.get('/company/'+companyID)
							.then(
									function(response){
										return response.data;
									},
									function(errResponse){
										console.error('Error while loading company');
										return $q.reject(errResponse);
									}
							);
			},

		    createCompany: function(company){
					return $http.post('/company', company)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating company');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    updateCompany: function(company, id){
					return $http.put('/company/'+id, company)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating company');
										return $q.reject(errResponse);
									}
							);
			},
		    
			addOwner: function(id, owner){
					return $http.put('/company/'+id+'/owner',owner)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while adding an owner');
										return $q.reject(errResponse);
									}
							);
			}
		
	};

}]);
