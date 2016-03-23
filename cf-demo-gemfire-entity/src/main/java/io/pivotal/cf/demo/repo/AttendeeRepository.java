package io.pivotal.cf.demo.repo;

import org.springframework.data.gemfire.repository.GemfireRepository;

import io.pivotal.cf.demo.entity.Attendee;

/**
 * Gemfire repository for the Attendee entity.
 * 
 * @author 
 *
 */
public interface AttendeeRepository extends GemfireRepository<Attendee, String> {

}
