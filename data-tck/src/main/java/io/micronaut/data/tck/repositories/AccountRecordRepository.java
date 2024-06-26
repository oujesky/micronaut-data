/*
 * Copyright 2017-2020 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.data.tck.repositories;

import io.micronaut.data.annotation.WithTenantId;
import io.micronaut.data.annotation.WithoutTenantId;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.tck.entities.Account;
import io.micronaut.data.tck.entities.AccountRecord;

import java.util.List;

public interface AccountRecordRepository extends CrudRepository<AccountRecord, Long> {

    @WithoutTenantId
    List<AccountRecord> findAll$withAllTenants();

    @WithTenantId("bar")
    List<AccountRecord> findAll$withTenantBar();

    @WithTenantId("foo")
    List<AccountRecord> findAll$withTenantFoo();

    @WithTenantId("#{this.barTenant()}")
    List<AccountRecord> findAll$withTenantExpression();

    default String barTenant() {
        return "bar";
    }
}
