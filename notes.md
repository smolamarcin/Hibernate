## Default conructor in the entity class.

The no-argument constructor, which is also a JavaBean convention, is a requirement for all persistent classes. Hibernate needs to create objects for you, using Java Reflection. The constructor can be private. However, package or public visibility is required for runtime proxy generation and efficient data retrieval without bytecode instrumentation.

## Different cache types

L1 cache:
> Associated with concrete Session object
> Enabled by default, you can't turn it off in configuration
> It's not shared betwween threads.
> Contains the entities known for specific sessions
> Enties for this cache are deletetd when we close the session or clean it.
> evict() - delete one object, clear() - delete all objects

L2 cache:
> Associated with concrete SessionFactory
> Turned off by default, can turn on in the configuration
> Shared between threads
> Implementation can be provided from otuside (eg EHCache, JBossCache2)

Query cache
> stores query results

## Updating entities

Handle many of these kinds of situations. It helps to have several options.
  Another  issue  to  consider  is  dirty  checking.  Hibernate  automatically  detects  state
changes in order to synchronize the updated state with the database. It’s usually safe
to  return  a  different  instance  from  the  getter  method  than  the  instance  passed  by
Hibernate to the setter. Hibernate comparesthem by value—not by object identity—to determine whether the
attribute’s persistent state needs to be updated.
